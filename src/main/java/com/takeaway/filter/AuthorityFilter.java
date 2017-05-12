package com.takeaway.filter;

//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorityFilter /*extends OncePerRequestFilter*/ {

    private static final String[] loginUrl = {
            "/myCenter/**"
    };

//    private List<RequestMatcher> getMatcherList() {
//        List<RequestMatcher> matchers = new ArrayList<>();
//        for (String url : loginUrl) {
//            matchers.add(new AntPathRequestMatcher(url));
//        }
//
//        return matchers;
//    }


//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest httpServletRequest,
//            HttpServletResponse httpServletResponse,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//
//        List<RequestMatcher> matchers = getMatcherList();
//
//        for (RequestMatcher matcher : matchers) {
//            if (matcher.matches(httpServletRequest) &&
//                    httpServletRequest.getSession().getAttribute("userInfo") == null) {
//                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index?needLogin=true");
//            }
//        }
//
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//
//    }
}
