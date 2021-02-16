/***********************************************************************/
/**         Copyright (C) 2020-2030 西安三码客软件科技有限公司             */
/**                      All rights reserved                           */
/***********************************************************************/

package com.smk.cpp.manage.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @ClassName: MessageUtils
 * @Author: Mr.Jin-晋
 * @Date: 2021-02-10 23:49
 * @Version: V-0.0.1
 * @Description: TODO
 */
@Component
public class MessageUtils {

    private static final Logger logger = LoggerFactory.getLogger(MessageUtils.class);

    private static MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 不带占位符
     * @param key
     * @return
     */
    public static String getMessage(String key){
        try {
            return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException noSuchMessageException) {
            logger.error("get message error:key is {}" , key);
            return "message undefined";
        }

    }

    /**
     * 带占位符的
     * @param key
     * @return
     */
    public static String getMessage(String key, String defaultMessage){
        try {
            return messageSource.getMessage(key, null,defaultMessage, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            logger.error("get message error:key is {}" , key);
            logger.error("get message error:defaultMessage is {}" , defaultMessage);
            logger.error("get message error:message is {} , cause is {}" , e.getMessage() , e.getCause());
            return "message undefined";
        }
    }

    /**
     * 带占位符的
     * @param key
     * @param objects
     * @return
     */
    public static String getMessage(String key, Object[] objects){
        try {
            return messageSource.getMessage(key, objects, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            logger.error("get message error:key is {}" , key);
            logger.error("get message error:objects is {}" , objects);
            logger.error("get message error:message is {} , cause is {}" , e.getMessage() , e.getCause());
            return "message undefined";
        }

    }/**
     * 带默认值占位符的
     * @param key
     * @param objects
     * @return
     */
    public static String getMessage(String key, Object[] objects, String defaultMessage){
        try {
            return messageSource.getMessage(key, objects, defaultMessage, LocaleContextHolder.getLocale());
        } catch (NoSuchMessageException e) {
            logger.error("get message error:key is {}" , key);
            logger.error("get message error:objects is {}" , objects);
            logger.error("get message error:defaultMessage is {}" , defaultMessage);
            logger.error("get message error:message is {} , cause is {}" , e.getMessage() , e.getCause());
            return "message undefined";
        }
    }

}
