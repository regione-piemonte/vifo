/*
 * SPDX-FileCopyrightText: (C) Copyright 2024 Regione Piemonte
 * 
 * SPDX-License-Identifier: EUPL-1.2
*/
package it.csi.vifo.vifobo.business;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Spring application context helper.
 *
 * @author CSI PIEMONTE
 */
public class SpringApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext appContext;

    private static final Map<String, Object> beanCache = new HashMap<>();

    private static final Map<String, Object> restEasyServiceCache = new HashMap<>();

    // Private constructor prevents instantiation from other classes
    private SpringApplicationContextHelper() {}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }


    /**
     * getBean
     *
     * @param beanName  beanName
     * @param cacheable cacheable
     * @return oject bean
     */
    public static Object getBean(String beanName, boolean cacheable) {

        if (cacheable && beanCache.containsKey(beanName)) {
            return beanCache.get(beanName);
        }

        Object bean = null;

        if (appContext.containsBean(beanName)) {
            bean = appContext.getBean(beanName);
        } else {
            bean = appContext.getBean(beanName.substring(0, 1).toLowerCase() + beanName.substring(1));
        }

        if (cacheable) {
            beanCache.put(beanName, bean);
        }

        return bean;
    }

    /**
     * getBean
     *
     * @param beanName beanName
     * @return object bean
     */
    public static Object getBean(String beanName) {
        return getBean(beanName, true);
    }

}