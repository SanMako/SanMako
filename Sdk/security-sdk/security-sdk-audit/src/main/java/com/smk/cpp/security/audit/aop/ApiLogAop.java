/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.security.audit.aop;

import com.smk.cpp.base.tool.StringTools;
import com.smk.cpp.security.audit.enums.LogTypeEnum;
import com.smk.cpp.security.audit.enums.RiskEnum;
import com.smk.cpp.security.audit.model.AuditLog;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能描述：
 *
 * @ClassName: ApiLogAop
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-14 12:07
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Aspect
@Component
public class ApiLogAop {

    private static final Logger logger = LoggerFactory.getLogger(ApiLogAop.class);

    @Value("${spring.application.name}")
    private String module;

    @Pointcut("execution(* com.smk.cpp.*.controller.*.*(..))")
    public void cutService(){}

    @Before("cutService()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        AuditLog auditLog = build(joinPoint);
    }

    /**
     * 获取当前系统id
     */
    private String loadSystemId() {
        return "";
    }

    /**
     * 获取当前用户
     * @return
     */
    private String loadOperatorId () {
        return "";
    }

    /**
     * 获取客户端IP
     * 1、客户端远程IP
     * 2、服务接口调用方IP
     * 3、服务所在本机IP
     *
     * @return 客户端IP
     */
    private String loadClientIp() {
        return "";
    }

    /**
     * 敏感词过滤，防止日志注入
     *
     * @param auditLog
     */
    private void doSafeFilter(AuditLog auditLog) {
        String content = auditLog.getContent();
        content = doFilterSensitiveWords(content);
        content = doFilterCRLF(content);
        auditLog.setContent(content);
    }

    /**
     * 过滤换行符
     * @param content
     * @return
     */
    private String doFilterCRLF(String content) {
        return content.replaceAll("\r", "(r)")
                .replaceAll("\n", "(n)")
                .replaceAll("\\[", "(")
                .replaceAll("]", ")");
    }

    /**
     * 替换关键词
     * @param content
     * @return
     */
    private String doFilterSensitiveWords(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        Pattern sensitivePattern = getSensitivePattern("");
        Matcher matcher = sensitivePattern.matcher(content);
        if (0 == matcher.groupCount()) {
            return content;
        }
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "*****");
            sb.append(matcher.group(1));
            sb.append("*****");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 生成敏感词过滤正则
     * @param customSensitiveWords
     * @return
     */
    private Pattern getSensitivePattern(String customSensitiveWords) {
        String sensitiveWords =
                "session|decrypt|key|pwd|crypto|encrypt|secret|passwd|password|uupd|credential";
        if (!StringUtils.isEmpty(customSensitiveWords)) {
            sensitiveWords = sensitiveWords + customSensitiveWords;
        }
        String sensitiveWordsRegex = ".{0,6}(" + sensitiveWords + ").{0,6}";
        return Pattern.compile(sensitiveWordsRegex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * AOP获取 @PostResource 和 @GetResource 属性信息
     *
     * @param joinPoint joinPoint对象
     * @return 返回K, V格式的参数，key是参数名称，v是参数值
     * @author liuhanqing
     * @date 2020/12/22 21:18
     */
    private AuditLog build(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();

        logger.info(method.getAnnotatedReturnType().getAnnotations().toString());
        AuditLog auditLog = new AuditLog();
        auditLog.setLogId(StringTools.idGenerate());
        auditLog.setLogType(LogTypeEnum.OPERATION.getTypes());
        if (method.getName().contains("login")) {
            auditLog.setLogType(LogTypeEnum.LOGIN.getTypes());
        }
//        auditLog.setTarget(operateTarget);
//        auditLog.setContent(operateContent);
        auditLog.setRequestUrl(request.getRequestURI());
        auditLog.setParams(Arrays.toString(joinPoint.getArgs()));
        auditLog.setSystemId(loadSystemId());
        auditLog.setModule(module);
        auditLog.setServerIp(request.getServerName());
        auditLog.setClientIp(loadClientIp());
        auditLog.setOperateId(loadOperatorId());
        auditLog.setCreateTime(System.currentTimeMillis());

        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        PostMapping postMapping = method.getAnnotation(PostMapping.class);
        PutMapping putMapping = method.getAnnotation(PutMapping.class);
        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
        ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);

        if (getMapping != null) {
            auditLog.setRiskLevel(RiskEnum.LOW.getRiskLevel());
            auditLog.setHttpMethod(RequestMethod.GET.name());
        }

        if (postMapping != null) {
            auditLog.setRiskLevel(RiskEnum.MIDDLE.getRiskLevel());
            auditLog.setHttpMethod(RequestMethod.POST.name());
        }

        if (putMapping != null) {
            auditLog.setRiskLevel(RiskEnum.MIDDLE.getRiskLevel());
            auditLog.setHttpMethod(RequestMethod.PUT.name());
        }

        if (deleteMapping != null) {
            auditLog.setRiskLevel(RiskEnum.HIGH.getRiskLevel());
            auditLog.setHttpMethod(RequestMethod.DELETE.name());
        }

        if (apiOperation != null) {
            auditLog.setOperateName(apiOperation.value());
        }
        return auditLog;
    }

    @AfterReturning(returning = "ret", pointcut = "cutService()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("after return ==> " + ret);

    }

    @AfterThrowing(pointcut = "cutService()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) throws Throwable {
        logger.info("after Throwing ==> " + ex);
    }

}
