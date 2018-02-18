package com.hurricane.app.cartoon.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class CharsetEncodingFilter implements Filter{
	private Logger logger = Logger.getLogger(getClass().getName());
	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		String requestURL = ((HttpServletRequest)servletrequest).getRequestURI();
		HttpServletRequest request = (HttpServletRequest)servletrequest;
		servletresponse.setCharacterEncoding("UTF-8");
		servletresponse.setContentType("text/html;charset=UTF-8");
		servletrequest.setCharacterEncoding("UTF-8");
		logger.info("filter---"+((HttpServletRequest)servletrequest).getRequestURI());
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
