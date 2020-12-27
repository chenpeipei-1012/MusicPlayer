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
 * 权限过滤器
 * @author 华为MateBook 13
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
			// 登录才允许访问
			// 判断是否登录
			HttpSession session = httpRequest.getSession();
			User user = (User) session.getAttribute("user");
			
			if(user == null){
				// 未登录，不允许访问。重定向到登录页面
				if(header == null){
					httpResponse.sendRedirect("/MusicPlayer/login.html");
				}else{
					httpResponse.setContentType("application/json; charset=utf-8");
					PrintWriter out = httpResponse.getWriter();
					// JSON对象
					JSONObject json = new JSONObject();
					json.put("isLogin", "no");
					out.print(json);
					
					// 把数据响应给AJAX
					out.flush();
			        out.close();
				}
				
				System.out.println("user == null");
			}else{
				// pass
				chain.doFilter(request, response);
			}
		}else{
			// 直接访问
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
