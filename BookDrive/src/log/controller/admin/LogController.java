package log.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.admin.Action;
import vo.admin.ActionForward;
import log.action.admin.LoginAction;
import log.action.admin.LogoutAction;
import log.action.admin.MainAction;

@WebServlet("*.logC")
public class LogController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		System.out.println("(관리자)요청URL : " + command);
		if(command.equals("/AdminloginForm.logC")){ //로그인 폼
			request.setAttribute("pagefile", "/member/admin_loginForm.jsp");
			forward=new ActionForward();
			forward.setPath("admin_template.jsp");
		}
		else if (command.equals("/Adminlogin.logC")){ //로그인 프로세스
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/Adminlogout.logC")) { //로그아웃 프로세스
			action = new LogoutAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/Adminmain.logC")) { //메인화면
			action = new MainAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//		else if (command.equals("/main.log")) { //메인화면 
//			request.setAttribute("pagefile", "/member/main.jsp");
//			forward=new ActionForward();
//			forward.setPath("template.jsp");
//		}
		
		if(forward != null) {
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath()); //경로가 나옴
			}else{
				RequestDispatcher dispatcher=
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response); //경로가 나오지 않음
			}
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request,response);
	}   
	
}