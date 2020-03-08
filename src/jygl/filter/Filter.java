package jygl.filter;

import javax.servlet.*;
import java.io.IOException;

public class Filter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding("utf8");
        response.setContentType("text/html;charset=utf8");
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
