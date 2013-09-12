package net.bitacademy.java41.servlets.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.vo.Member;

@WebServlet("/auth/addmember")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = 
				request.getRequestDispatcher("/auth/addmember.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDao memberDao = 
				(MemberDao) this.getServletContext().getAttribute("memberDao");
		
		try {
			/* post 요청 데이터의 한글 처리 
			 * 반드시 최초의 getParameter() 호출 전에 먼저 설정해야 한다.
			 */
			request.setCharacterEncoding("UTF-8");
			
			Member member = new Member()
							.setEmail(request.getParameter("email"))
							.setName(request.getParameter("name"))
							.setPassword(request.getParameter("password"))
							.setTel(request.getParameter("tel"))
							.setBlog(request.getParameter("blog"))
							.setDetailAddress(request.getParameter("detailAddr"))
							.setTag(request.getParameter("tag"))
							.setLevel(Integer.parseInt(request.getParameter("level")));
			
			memberDao.add(member);
			
			// 로그인 처리!
			
			
			// Redirect나 Refresh를 지정할 때,
			// 클라이언트가 다시 서버에 요청하는 것이므로, 
			// 컨트롤러의 주소를 주도록한다. 
			response.sendRedirect("../auth/allmember");
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
}











