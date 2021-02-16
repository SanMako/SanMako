/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.manage.common.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * 功能描述：
 *
 * @ClassName: SmkLocaleResolver
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-10 23:45
 * @Version: V-0.0.1
 * @Description: TODO
 */
public class SmkLocaleResolver implements LocaleResolver {

    private static final Logger logger = LoggerFactory.getLogger(SmkLocaleResolver.class);

    private static final String PATH_PARAMETER = "SanMaKo_Accept_Lang";

    private static final String REGEX_UNDERLINERRR = "_";

    private static final String I18N_LANGUAGE_SESSION = "SanMaKo_i18n_language_session";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader(PATH_PARAMETER);
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(language) && language.indexOf(REGEX_UNDERLINERRR) > 0) {
            String[] languages = language.split(REGEX_UNDERLINERRR);
            if (languages.length == 2) {
                locale = new Locale(languages[0], languages[1]);
            }
            HttpSession session = request.getSession();
            session.setAttribute(I18N_LANGUAGE_SESSION, locale);
        } else {
            //如果没有带国际化参数，则判断session有没有保存，有保存，则使用保存的，也就是之前设置的，避免之后的请求不带国际化参数造成语言显示不对
            HttpSession session = request.getSession();
            Locale localeInSession = (Locale) session.getAttribute(I18N_LANGUAGE_SESSION);
            if (localeInSession != null) {
                locale = localeInSession;
            }
        }
        logger.info("i18n current language value is {}", locale.getLanguage());
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }

}