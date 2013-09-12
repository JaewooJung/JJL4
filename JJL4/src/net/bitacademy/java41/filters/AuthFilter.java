package net.bitacademy.java41.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bitacademy.java41.vo.Member;

public class AuthFilter implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(
			ServletRequest req, ServletResponse resp,
			FilterChain next) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		Member member = (Member)request.getSession().getAttribute("member");
		
		if (member != null || /*로그인 했다면*/
				request.getServletPath().contains("auth/login") || /* 로그인/로그아웃 요청인 경우*/
				request.getServletPath().contains("auth/LoginForm.html") ||
				request.getServletPath().contains("member/signinForm.do") ||
				request.getServletPath().contains("member/signin.do")) /* 회원가입인 경우 */
		{ 
			/*if(request.getServletPath().startsWith("/auth/login")){
				response.sendRedirect(
						request.getServletContext().getContextPath() + 
						"/auth/login");
			}else{*/
			next.doFilter(req, resp);
			//}
			
		} else {
			response.sendRedirect(
					request.getServletContext().getContextPath() + 
					"/auth/loginForm.do");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
