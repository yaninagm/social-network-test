package com.schibsted.spain.friends.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Configuration
public class DataSource implements ServletContextInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        initH2Console(servletContext);
    }

    private void initH2Console(ServletContext servletContext) {
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", "");
        h2ConsoleServlet.addMapping("/h2console/*");
        h2ConsoleServlet.setLoadOnStartup(1);
    }
}
