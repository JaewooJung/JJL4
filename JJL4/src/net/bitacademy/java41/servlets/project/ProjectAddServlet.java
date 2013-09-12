package net.bitacademy.java41.servlets.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.vo.Project;

@WebServlet("/auth/addproject")
@SuppressWarnings("serial")
public class ProjectAddServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = 
				request.getRequestDispatcher("/auth/addproject.jsp");
		rd.forward(request, response);
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ProjectDao projectDao = 
				(ProjectDao) this.getServletContext().getAttribute("projectDao");
		
		try {
			/* post 요청 데이터의 한글 처리 
			 * 반드시 최초의 getParameter() 호출 전에 먼저 설정해야 한다.
			 */
			request.setCharacterEncoding("UTF-8");
			
			Project project = new Project()
			
			/*stmt.setInt(1, 1);
			stmt.setString(2, project.getTitle());
			stmt.setString(3, project.getContent());
			stmt.setDate(4, project.getStartDate());
			stmt.setDate(5, project.getEndDate());
			stmt.setString(6, project.getTag());
			stmt.setInt(7, project.getLevel());*/
							.setTitle(request.getParameter("title"))
							.setContent(request.getParameter("content"))
							.setStartDate(Date.valueOf(request.getParameter("startdate")))
							.setEndDate(Date.valueOf(request.getParameter("enddate")))
							.setTag(request.getParameter("tag"));
			
			projectDao.add(project);
			
			// 로그인 처리!
			
			
			// Redirect나 Refresh를 지정할 때,
			// 클라이언트가 다시 서버에 요청하는 것이므로, 
			// 컨트롤러의 주소를 주도록한다. 
			response.sendRedirect("../auth/allproject");
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
}











