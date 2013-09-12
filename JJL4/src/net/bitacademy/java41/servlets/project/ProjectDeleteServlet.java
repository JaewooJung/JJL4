package net.bitacademy.java41.servlets.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.vo.Project;
import net.bitacademy.java41.vo.Project;

@WebServlet("/auth/projectdelete")
@SuppressWarnings("serial")
public class ProjectDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ProjectDao projectDao = 
				(ProjectDao) this.getServletContext().getAttribute("projectDao");
		try {
			
			projectDao.remove(Integer.parseInt(request.getParameter("pno")));
			HttpSession session = request.getSession();
			RequestDispatcher rd = request.getRequestDispatcher("../auth/allproject");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = 
					request.getRequestDispatcher("/error.jsp");
			rd.forward(request, response);
		}
	}
	

}







