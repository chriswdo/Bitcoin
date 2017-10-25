package com.eshore.datasupport.common.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter {
	private String excludeUrl;

	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludeUrl = filterConfig.getInitParameter("excludeUrl");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
		 HttpServletResponse httpresponse = (HttpServletResponse) response;
		
		 //暂时不拦截ajax请求
		 String requestType = httpRequest.getHeader("X-Requested-With");  
		 String currentURL = httpRequest.getRequestURI(); 
		 HttpSession session = httpRequest.getSession(false);  
		 if("XMLHttpRequest".equals(requestType) ){
			 chain.doFilter(request, response); 
			 return ;
		 }
		 String [] excludeUrls = excludeUrl.split(",");
		 for(String str : excludeUrls){
			 if(currentURL.contains(str)){
				 chain.doFilter(request, response); 
				 return ;
			 }
		 }
		 if(session == null || session.getAttribute(Conts.SESSIONFLAG) == null){
			 java.io.PrintWriter out = httpresponse.getWriter();  
		     out.println("<html>");  
		     out.println("<script>");  
		     out.println("top.location.href='"+httpRequest.getContextPath()+"/login.jsp'");  
		     out.println("</script>");  
		     out.println("</html>"); 
			 return ;
		 }else{
			 chain.doFilter(request, response);
			 return ;
		 }
		
	}

	@Override
	public void destroy() {
		// do nothing
	}

}
