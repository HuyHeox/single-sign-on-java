package com.ks.sso.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

public class CustomListener implements ServletContextListener {

    Logger logger = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("CustomListener is initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("CustomListener is destroyed");
    }
    
    
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> customListenerBean() {
        ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean();
        bean.setListener(new CustomListener());
        return bean;
    }
}