package net.bitacademy.java41.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.bitacademy.java41.controls.MainControl;
import net.bitacademy.java41.controls.auth.LoginControl;
import net.bitacademy.java41.controls.auth.LoginFormControl;
import net.bitacademy.java41.controls.auth.LogoutControl;
import net.bitacademy.java41.controls.member.AllmemberControl;
import net.bitacademy.java41.controls.member.MemberAddControl;
import net.bitacademy.java41.controls.member.MemberAddFormControl;
import net.bitacademy.java41.controls.member.MemberDeleteControl;
import net.bitacademy.java41.controls.member.MemberDetailControl;
import net.bitacademy.java41.controls.member.MemberUpdateControl;
import net.bitacademy.java41.controls.member.MemberUpdateFormControl;
import net.bitacademy.java41.controls.member.PasswordResetControl;
import net.bitacademy.java41.controls.member.PasswordResetFormControl;
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
		
		AuthService authService = new AuthService().setMemberDao(memberDao)
													.setDBConnectionPool(dbpool);
		MemberService memberService = new MemberService().setMemberDao(memberDao)
														 .setDBConnectionPool(dbpool);
		
		ProjectService projectService = 
				new ProjectService().setDBConnectionPool(dbpool)
									.setProjectDao(projectDao);
		
		
		ctx.setAttribute("rootPath", ctx.getContextPath());
		ctx.setAttribute("/auth/loginForm.do", new LoginFormControl());
		ctx.setAttribute("/auth/login.do", new LoginControl().setAuthService(authService));
		ctx.setAttribute("/member/signinForm.do", new SigninFormControl());
		ctx.setAttribute("/member/signin.do", new SigninControl().setMemberService(memberService));
		ctx.setAttribute("/main.do", new MainControl().setProjectService(projectService));
		ctx.setAttribute("/auth/logout.do", new LogoutControl());
		ctx.setAttribute("/project/allproject.do", new AllprojectControl().setProjectService(projectService));
		ctx.setAttribute("/member/allmember.do", new AllmemberControl().setMemberService(memberService));
		ctx.setAttribute("/member/memberdetail.do", new MemberDetailControl().setMemberService(memberService));
		ctx.setAttribute("/member/memberupdateForm.do", new MemberUpdateFormControl().setMemberService(memberService));
		ctx.setAttribute("/member/memberupdate.do", new MemberUpdateControl().setMemberService(memberService));
		ctx.setAttribute("/member/passwordresetForm.do", new PasswordResetFormControl());
		ctx.setAttribute("/member/passwordreset.do", new PasswordResetControl().setMemberService(memberService));
		ctx.setAttribute("/member/memberaddForm.do", new MemberAddFormControl());
		ctx.setAttribute("/member/memberadd.do", new MemberAddControl().setMemberService(memberService));
		ctx.setAttribute("/member/memberdelete.do", new MemberDeleteControl().setMemberService(memberService));
		
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
