package com.example.demo.app.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Необходимо объявить и сопоставить DispatcherServlet
// для обработки всех запросов.
// https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-servlet.html
// https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-servlet/context-hierarchy.html
// Можно использовать класс AbstractAnnotationConfigDispatcherServletInitializer
// для регистрации и программной инициализации DispatcherServlet.
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                AppContext.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
