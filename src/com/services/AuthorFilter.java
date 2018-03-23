package com.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet Filter implementation class AuthorFilter
 */
@Controller
public class AuthorFilter implements Filter {
    private Logger log = Logger.getLogger(this.getClass());
    public static List<String> passUrlList = new ArrayList<String>();

    /**
     * Default constructor.
     */
    public AuthorFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
        passUrlList = null;
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            String url = req.getServletPath();
            if (url.endsWith("jsp") || url.endsWith("html")) {
                boolean isPass = false;
                for(String s : passUrlList){
                    if(url.indexOf(s) != -1){
                        isPass = true;
                        break;
                    }
                }

                if (isPass){
                    chain.doFilter(request, response);
                } else {
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub

        passUrlList.add("login.jsp");
        passUrlList.add("userLogin.html");
        passUrlList.add("ddlogin.html");
        passUrlList.add("trans_target_list");
        passUrlList.add("project_view.jsp");
        passUrlList.add("member_app_view");

    }

}
