package com.supinfo.test;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Théo on 10/06/2016.
 *
 *
 *
 * <filter>
 <filter-name>cors</filter-name>
 <filter-class>com.supinfo.test.CorsFilter</filter-class>
 </filter>

 <filter-mapping>
 <filter-name>cors</filter-name>
 <url-pattern>/rest/*</url-pattern>
 </filter-mapping>
 */
public class CorsFilter implements  Filter {


    private static final Logger log = Logger.getAnonymousLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Adding Access Control Response Headers");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response .setHeader("Content-Type","application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, HEAD, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers","gareD,gareA,heureD,heureR,dateD,dateR,Access-Control-Allow-Headers, search, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}