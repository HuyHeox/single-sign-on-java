package com.ks.sso.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

public class CustomServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(CustomServlet.class);

    @Override
    protected void doGet(
        HttpServletRequest req,
        HttpServletResponse resp) throws ServletException, IOException {
            logger.info("CustomServlet doGet() method is invoked");
            super.doGet(req, resp);
    }

    @Override
    protected void doPost(
        HttpServletRequest req,
        HttpServletResponse resp) throws ServletException, IOException {
            logger.info("CustomServlet doPost() method is invoked");
            super.doPost(req, resp);
    }
    
    @Bean
    public ServletRegistrationBean customServletBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new CustomServlet(), "/servlet");
        return bean;
    }
}
