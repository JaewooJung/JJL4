package net.bitacademy.java41.servlets.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.vo.Member;
import net.bitacademy.java41.vo.Project;

@WebServlet("/auth/projectdetail")
@SuppressWarnings("serial")
public class ProjectDetailServlet extends HttpServlet {
	
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProjectDao projectDao = 
				(ProjectDao) this.getServletContext().getAttribute("projectDao");
		MemberDao memberDao = 
				(MemberDao) this.getServletContext().getAttribute("memberDao");
		
		
		try {
			HttpSession session = ((HttpServletRequest) request).getSession();
			Member member = (Member) session.getAttribute("member");
			String pno = request.getParameter("pno");
			Project project = projectDao.getProject(Integer.parseInt(pno));
			RequestDispatcher rd = request.getRequestDispatcher("projectdetail.jsp");
			session.setAttribute("project_detail", project);
			session.setAttribute("project_detail_members", memberDao.get(Integer.parseInt(request.getParameter("pno"))));
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}