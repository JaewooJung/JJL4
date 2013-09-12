package net.bitacademy.java41.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.bitacademy.java41.controls.MainControl;
import net.bitacademy.java41.controls.auth.LoginControl;
import net.bitacademy.java41.controls.auth.LoginFormControl;
import net.bitacademy.java41.controls.auth.LogoutControl;
import net.bitacademy.java41.controls.member.AllmemberControl;
import net.bitacademy.java41.controls.member.MemberDetailControl;
import net.bitacademy.java41.controls.member.SigninControl;
import net.bitacademy.java41.controls.member.SigninFormControl;
import net.bitacademy.java41.controls.project.AllprojectControl;
import net.bitacademy.java41.dao.MemberDao;
import net.bitacademy.java41.dao.ProjectDao;
import net.bitacademy.java41.services.AuthService;
import net.bitacademy.java41.services.MemberService;
import net.bitacademy.java41.services.ProjectService;
import net.bitacademy.java41.util.DBConnectionPool;

public class ContextLoaderListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext ctx = event.getServletContext();
		
		DBConnectionPool dbpool = new DBConnectionPool(
				ctx.getInitParameter("dburl"), 
				ctx.getInitParameter("user"), 
				ctx.getInitParameter("password"),
				ctx.getInitParameter("driverClass"));
		MemberDao memberDao = new MemberDao(dbpool);
		ProjectDao projectDao = new ProjectDao(dbpool);
		
		
		ctx.setAttribute("rootPath", ctx.getContextPath());
		ctx.setAttribute("/auth/loginForm.do", new LoginFormControl());
		ctx.setAttribute("/auth/login.do", new LoginControl()
											.setMemberDao(memberDao));
		ctx.setAttribute("/member/signinForm.do", new SigninFormControl());
		ctx.setAttribute("/member/signin.do", new SigninControl()
												.setMemberDao(memberDao) );
		
		AuthService authService = new AuthService().setMemberDao(memberDao);				
		MemberService memberService = 
								new MemberService().setMemberDao(memberDao)
												.setDBConnectionPool(dbpool);
		ProjectService projectService = 
								new ProjectService()
												.setDBConnectionPool(dbpool)
												.setProjectDao(projectDao);
		
		
		ctx.setAttribute("/main.do", new MainControl()
										.setProjectDao(projectDao));
		
		ctx.setAttribute("/auth/logout.do", new LogoutControl());
		
		ctx.setAttribute("/project/allproject.do", new AllprojectControl().setProjectDao(projectDao));
		
		ctx.setAttribute("/member/allmember.do", new AllmemberControl().setMemberDao(memberDao));
		ctx.setAttribute("/member/memberdetail.do", new MemberDetailControl().setMemberDao(memberDao));
		
		
		/*
		ctx.setAttribute("/project/list.do", new ProjectListControl()
												.setProjectDao(projectDao));
		ctx.setAttribute("/project/view.do", new ProjectViewControl()
												.setProjectDao(projectDao));
		ctx.setAttribute("/project/addForm.do", new ProjectAddFormControl());
		ctx.setAttribute("/project/add.do", new ProjectAddControl()
												.setProjectDao(projectDao));
		ctx.setAttribute("/member/list.do", new MemberListControl()
												.setMemberDao(memberDao));
		ctx.setAttribute("/member/view.do", new MemberViewControl()
												.setMemberDao(memberDao));
		ctx.setAttribute("/member/passwordChange.do", new PasswordChangeControl()
												.setMemberDao(memberDao));	
		ctx.setAttribute("/member/add.do", new MemberAddControl()
											.setMemberDao(memberDao));
		*/
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
