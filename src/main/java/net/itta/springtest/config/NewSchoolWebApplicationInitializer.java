
package net.itta.springtest.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class NewSchoolWebApplicationInitializer
    extends AbstractAnnotationConfigDispatcherServletInitializer
{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootConfig.class}; //ou null
    }

    @Override
    protected Class<?>[] getServletConfigClasses()  {
        return new Class[] {WebAppContext .class}; 
       
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

}