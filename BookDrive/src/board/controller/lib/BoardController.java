package board.controller.lib;

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
import board.action.lib.BoardNoticeDetailAction;
import board.action.lib.BoardNoticeListAction;
import board.action.admin.BoardWriteProAction;
import board.action.lib.BoardQnADetailAction;
import board.action.lib.BoardQnAListAction;
import board.action.lib.BoardRepleWriteFormAction;
import board.action.lib.BoardRepleWriteProAction;
import board.action.lib.BoardReplyModifyFormAction;
import board.action.lib.BoardReplyModifyProAction;
import board.action.lib.BoardWishBookDetailAction;
import board.action.lib.BoardWishBookListAction;
import vo.admin.ActionForward;
import vo.admin.Action;
import vo.admin.ActionVoid;

@WebServlet("*.boardL")
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
		if(command.equals("/BoardList1.boardL")){ //공지사항 리스트
			action = new BoardNoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardNoticeDetail.boardL")){ //공지사항 상세보기
			action = new BoardNoticeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardList2.boardL")){ //QnA 리스트
			action = new BoardQnAListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardQnADetail.boardL")){ //QnA상세보기 
			action = new BoardQnADetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardRepleWriteForm.boardL")){ //답변(QnA) 작성 폼
			action = new BoardRepleWriteFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardRepleWritePro.boardL")){ //답변(QnA)
			action = new BoardRepleWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardReplyModifyForm.boardL")){ //답글수정 Form
			action = new BoardReplyModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardReplyModifyPro.boardL")){ //답글수정 Process
			action = new BoardReplyModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardList3.boardL")){ //희망도서 리스트
			action = new BoardWishBookListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardWishBookDetail.boardL")){ //QnA상세보기 
			action = new BoardWishBookDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardList4.boardL")){ //자유게시판 리스트
			action = new BoardFreeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		else if(command.equals("/BoardWriteForm.boardL")){ //공지사항,QnA 작성 폼
			request.setAttribute("pagefile", "/board/admin_boardWriteForm.jsp");
			forward = new ActionForward();
			forward.setPath("admin_template.jsp");
		}
		else if(command.equals("/BoardWritePro.boardL")){ //글 작성(selectbox로 구분값 주기)
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/BoardModifyForm.boardL")){ //수정 Form
			action = new BoardModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/BoardModifyPro.boardL")){ //수정 Process
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/BoardDeletePro.boardL")){ //답글수정 Process
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/fileDown.boardL")) {//파일 다운로드
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