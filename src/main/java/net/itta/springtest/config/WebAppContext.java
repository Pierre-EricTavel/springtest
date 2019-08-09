/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.springtest.config;

import java.util.Properties;
import net.itta.springtest.LeServiceExpress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@EnableWebMvc
@ComponentScan( basePackages = {"net.itta.springtest" } )
public class WebAppContext implements WebMvcConfigurer{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
       configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
    
    
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/jsp/", ".jsp");
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
    
    @Bean
    public SimpleMappingExceptionResolver exceptionResolver(){
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        Properties exceptionsProp = new Properties();
        exceptionsProp.put("net.itta.springtest.MyException", "error/404"); 
        exceptionsProp.put("java.lang.RuntimeException", "error/error");
        exceptionsProp.put("java.lang.Exception", "error/500");
        exceptionResolver.setExceptionMappings(exceptionsProp);

        Properties statusProp = new Properties();
        statusProp.put("error/404", "404");
        statusProp.put("error/500", "500");
        
        exceptionResolver.setStatusCodes(statusProp);

        return exceptionResolver;
    }

    @Override
    public Validator getValidator() {
        return WebMvcConfigurer.super.getValidator(); 
    }
    
    
    
}
