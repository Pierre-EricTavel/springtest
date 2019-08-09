/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.itta.springtest.config;

import javax.servlet.*;
import org.springframework.web.*;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.*;
import org.springframework.web.servlet.*;


public class OldSchoolWebAppInitializer{ //  implements WebApplicationInitializer{ le classique

    //@Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppContext.class);
    
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
        servletContext.addListener(new ContextLoaderListener(context));
    }
    
}
