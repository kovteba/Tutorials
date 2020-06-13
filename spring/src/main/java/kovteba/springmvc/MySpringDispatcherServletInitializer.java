package kovteba.springmvc;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


// Замена web.xml
public class MySpringDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[0];
   }

   // замена applicationContextMVC
   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[0];
   }

   @Override
   protected String[] getServletMappings() {
      return new String[0];
   }
}


class MyClass{

}