package com.mkyong.customer.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

public class CharacterEncodingFilter implements Filter {
  static Logger log = Logger.getLogger(CharacterEncodingFilter.class.getName());

  public void init(FilterConfig filterConfig) throws ServletException {}

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    servletRequest.setCharacterEncoding("UTF-8");
    log.info("请求中的姓名: " + servletRequest.getParameter("姓名"));
    log.info("请求中的地址: " + servletRequest.getParameter("address"));

    servletResponse.setContentType("text/html; charset=UTF-8");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  public void destroy() {

  }
}
