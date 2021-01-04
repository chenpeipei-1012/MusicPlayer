package controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 编码过滤器
 * @author 华为MateBook 13
 * @WebFilter("/encoding")
 */

public class EncodingFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httptRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String path = URLDecoder.decode(httptRequest.getRequestURI(),"UTF-8");

		// 设置响应内容格式和编码方式
		httpResponse.setContentType("application/json; charset=utf-8");
		httptRequest.setCharacterEncoding("utf-8");
		httpResponse.setCharacterEncoding("utf-8");

		path = path.substring(12);
		// 将请求转新URL
		if(path.contains("musicCloud")){
			System.out.println("path :" + path);
			httptRequest.getRequestDispatcher(path).forward(httptRequest, httpResponse);
		}
		else
			chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
