package lib.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.action.admin.LibApvListAction;
import lib.action.admin.LibApvProAction;
import lib.action.admin.LibApvRvoProAction;
import lib.action.admin.LibInfoAction;
import lib.action.admin.LibListAction;
import lib.action.admin.LibAdminListAction;
import vo.admin.Action;
import log.action.admin.LoginAction;
import log.action.admin.LogoutAction;
import vo.admin.ActionForward;
import vo.admin.ActionVoid;

@WebServlet("*.lib")
public class LibController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		ActionVoid actionvoid = null;
		System.out.println("(도서관)요청URL : " + command);
		if(command.equals("/LibraryList.lib")){ //도서관 리스트
//			request.setAttribute("pagefile", "/member/admin_loginForm.jsp");
//			forward=new ActionForward();
//			forward.setPath("admin_template.jsp");
			action = new LibListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibraryInfo.lib")){ //도서관 상세보기
			action = new LibInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibraryApvList.lib")){ //도서관 가입신청 승인 리스트
			action = new LibApvListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibraryApvPro.lib")){ //도서관 가입신청 승인하기 프로세스(ajax)
			actionvoid = new LibApvProAction();
			try {
				actionvoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibraryApvRvoPro.lib")){ //도서관 가입신청 승인 취소 프로세스(ajax)
			actionvoid = new LibApvRvoProAction();
			try {
				actionvoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/LibAdminList.lib")){ //도서관관리자 리스트
			action = new LibAdminListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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