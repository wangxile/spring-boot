package com.wangxile.springboot;

import com.wangxile.springboot.config.AppConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @Author:wangqi
 * @Description:
 * @Date:Created in 2019/7/14
 * @Modified by:
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Load Spring web application configuration
        //相当于application.xml
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        ac.refresh();

        // Create and register the DispatcherServlet
        //相当于web.xml 注册servlet
        DispatcherServlet servlet = new DispatcherServlet(ac);
        //把一个servlet注册给tomcat
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        //设置DispatcherServlet的访问路径
        registration.addMapping("*.do");
    }
}
