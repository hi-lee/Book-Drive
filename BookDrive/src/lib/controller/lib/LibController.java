package lib.controller.lib;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.action.lib.LibAdminInfoAction;
import lib.action.lib.LibAdminListAction;
import lib.action.lib.LibAdminModifyFormAction;
import lib.action.lib.LibAdminModifyProAction;
import lib.action.lib.LibInfoAction;
import lib.action.lib.LibListAction;
import lib.action.lib.LibModifyFormAction;
import lib.action.lib.LibModifyProAction;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.ActionVoid;

@WebServlet("*.libL")
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
		if (command.equals("/LibraryInfo.libL")){ //도서관 상세보기
			action = new LibInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibraryModifyForm.libL")) { //도서관 정보수정 폼
			action = new LibModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibraryModifyPro.libL")) { //도서관 정보수정 폼
			action = new LibModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibAdminInfo.libL")) { //도서관관리자 정보 보기
			action = new LibAdminInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibAdminModifyForm.libL")) { //도서관관리자 정보수정 폼
			action = new LibAdminModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (command.equals("/LibAdminModifyPro.libL")) { //도서관관리자 정보수정 프로세스
			action = new LibAdminModifyProAction();
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