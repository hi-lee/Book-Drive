package member.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.admin.Action;
import vo.admin.ActionForward;
import member.action.admin.AdminListAction;
import member.action.admin.MemberDeleteProAction;
import member.action.admin.MemberInfoAction;
import member.action.admin.MemberListAction;
import member.action.admin.MemberModifyProAction;
import member.action.admin.MemberModifyFormAction;

@WebServlet("*.memA")
public class MemController extends javax.servlet.http.HttpServlet 
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
		if(command.equals("/MemberList.memA")){ //일반회원 리스트
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberInfo.memA")) { //일반회원 정보보기
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberModifyForm.memA")) { //일반회원 정보수정 폼
			action = new MemberModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberModifyPro.memA")) { //일반회원 정보수정 프로세스
			action = new MemberModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberDeletePro.memA")) { //일반회원 삭제 프로세스
			action = new MemberDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/AdminList.memA")) { //통합관리자 리스트
			action = new AdminListAction();
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