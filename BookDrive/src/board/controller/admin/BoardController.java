package board.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.admin.BoardDeleteProAction;
import board.action.admin.BoardFreeListAction;
import board.action.admin.BoardModifyFormAction;
import board.action.admin.BoardModifyProAction;
import board.action.admin.BoardNoticeDetailAction;
import board.action.admin.BoardNoticeListAction;
import board.action.admin.BoardWriteProAction;
import board.action.admin.BoardQnADetailAction;
import board.action.admin.BoardQnAListAction;
import board.action.admin.BoardRepleWriteFormAction;
import board.action.admin.BoardRepleWriteProAction;
import board.action.admin.BoardReplyModifyFormAction;
import board.action.admin.BoardReplyModifyProAction;
import book.action.admin.BookImageInsertAction;
import book.action.admin.BookBrwListAction;
import book.action.admin.BookInfoAction;
import book.action.admin.BookListAction;
import book.action.admin.BookRevListAction;
import vo.admin.ActionForward;
import vo.admin.Action;
import vo.admin.ActionVoid;

@WebServlet("*.boardA")
public class BoardController extends javax.servlet.http.HttpServlet 
{
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		ActionVoid action2=null;
		Object object=null;
		System.out.println("(관리자)요청URL : " + command);
		if(command.equals("/BoardList1.boardA")){ //공지사항 리스트
			action = new BoardNoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardList2.boardA")){ //QnA 리스트
			action = new BoardQnAListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardList3.boardA")){ //.................희망도서 리스트(추가해야함)...................
			action = new BoardQnAListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardList4.boardA")){ //자유게시판 리스트
			action = new BoardFreeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardNoticeDetail.boardA")){ //공지사항 상세보기
			action = new BoardNoticeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardQnADetail.boardA")){ //QnA상세보기 
			action = new BoardQnADetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardWriteForm.boardA")){ //공지사항,QnA 작성 폼
			request.setAttribute("pagefile", "/board/admin_boardWriteForm.jsp");
			forward = new ActionForward();
			forward.setPath("admin_template.jsp");
		} else if(command.equals("/BoardWritePro.boardA")){ //글 작성(selectbox로 구분값 주기)
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardRepleWriteForm.boardA")){ //답변(QnA) 작성 폼
			action = new BoardRepleWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardRepleWritePro.boardA")){ //답변(QnA)
			action = new BoardRepleWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardModifyForm.boardA")){ //수정 Form
			action = new BoardModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/BoardModifyPro.boardA")){ //수정 Process
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else if(command.equals("/BoardReplyModifyForm.boardA")){ //답글수정 Form
			action = new BoardReplyModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardReplyModifyPro.boardA")){ //답글수정 Process
			action = new BoardReplyModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardDeletePro.boardA")){ //답글수정 Process
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/fileDown.boardA")) {//파일 다운로드
			forward=new ActionForward();
			forward.setPath("/board/file_down.jsp");
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