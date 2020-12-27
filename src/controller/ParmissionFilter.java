package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import entity.User;

/**
 * Ȩ�޹�����
 * @author ��ΪMateBook 13
 *
 */
public class ParmissionFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpRequest.getServletPath();
		
		String header = httpRequest.getHeader("X-Requested-With");
		
		if(path.startsWith("/user") || path.startsWith("/admin")){
			// ��¼���������
			// �ж��Ƿ��¼
			HttpSession session = httpRequest.getSession();
			User user = (User) session.getAttribute("user");
			
			if(user == null){
				// δ��¼����������ʡ��ض��򵽵�¼ҳ��
				if(header == null){
					httpResponse.sendRedirect("/MusicPlayer/login.html");
				}else{
					httpResponse.setContentType("application/json; charset=utf-8");
					PrintWriter out = httpResponse.getWriter();
					// JSON����
					JSONObject json = new JSONObject();
					json.put("isLogin", "no");
					out.print(json);
					
					// ��������Ӧ��AJAX
					out.flush();
			        out.close();
				}
				
				System.out.println("user == null");
			}else{
				// pass
				chain.doFilter(request, response);
			}
		}else{
			// ֱ�ӷ���
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
