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
 * ���������
 * @author ��ΪMateBook 13
 * @WebFilter("/encoding")
 */

public class EncodingFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httptRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String path = URLDecoder.decode(httptRequest.getRequestURI(),"UTF-8");

		// ������Ӧ���ݸ�ʽ�ͱ��뷽ʽ
		httpResponse.setContentType("application/json; charset=utf-8");
		httptRequest.setCharacterEncoding("utf-8");
		httpResponse.setCharacterEncoding("utf-8");

		path = path.substring(12);
		// ������ת��URL
		if(path.contains("musicCloud")){
			httptRequest.getRequestDispatcher(path).forward(httptRequest, httpResponse);
		}
		else
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
