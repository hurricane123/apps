package com.hurricane.app.cartoon.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hurricane.app.cartoon.controller.FileListController;


public class CharsetEncodingFilter implements Filter{
	private static Logger logger = LoggerFactory.getLogger(FileListController.class);
	private FilterConfig config;
	private String[] excludeUrls;
	public void init(FilterConfig filterconfig) throws ServletException {
		config = filterconfig;
		String excludeURL = config.getInitParameter("excludeURL");
		excludeUrls = excludeURL.split(",");
	}

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		String requestURL = ((HttpServletRequest)servletrequest).getRequestURI();
		HttpServletRequest request = (HttpServletRequest)servletrequest;
		servletresponse.setCharacterEncoding("UTF-8");
		servletresponse.setContentType("text/html;charset=UTF-8");
		servletrequest.setCharacterEncoding("UTF-8");
		String url = ((HttpServletRequest)servletrequest).getRequestURI();
		String contextPath = servletrequest.getServletContext().getContextPath();
//		String contextPath = config.getServletContext().getContextPath();
		logger.trace("应用上下文是："+contextPath);
		logger.trace("filter---"+url);
		for (String string : excludeUrls) {
			if ((contextPath+string.trim()).equals(url)) {//请求连接不要求进行乱码处理
				logger.trace("请求连接{}不要求进行乱码处理",url);
				filterchain.doFilter(servletrequest, servletresponse);
				return;
			}
		}
		if (((HttpServletRequest)servletrequest).getMethod().equalsIgnoreCase("post")) {
			filterchain.doFilter(servletrequest, servletresponse);
		}else { 
			filterchain.doFilter(new MyWrapperRequest((HttpServletRequest) servletrequest), servletresponse);
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	class MyWrapperRequest extends HttpServletRequestWrapper{

		public MyWrapperRequest(HttpServletRequest request) {
			super(request);
		}
		/**
		 * Returns the value of a request parameter as a String, 
		 * or null if the parameter does not exist. Request parameters are extra information sent with the request. 
		 * If the parameter might have more than one value, use getParameterValues(java.lang.String). 
		 */
		@Override
		public String getParameter(String name){
			// TODO Auto-generated method stub
			String value = super.getParameter(name);
			if (value==null||value.equals("")) {
				return value;
			}
			try {
				return new String(value.getBytes("ISO-8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return super.getParameter(name);
		}
		
		/**
		 * Returns an array of String objects containing all of the values the given request parameter has, 
		 * or null if the parameter does not exist. 
		 *	If the parameter has a single value, the array has a length of 1
		 */
		@Override
		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			String[] values = super.getParameterValues(name);
			if (values==null||values.equals("")) {
				return values;
			}
			for (int i = 0; i < values.length; i++) {
				try {
					if (values[i]!=null&&!values[i].equals("")) {
						values[i] = new String(values[i].getBytes("ISO-8859-1"),"UTF-8");
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return values;
		}
		
	}
	
	

}
