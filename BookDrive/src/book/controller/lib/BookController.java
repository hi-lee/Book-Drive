package book.controller.lib;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import book.action.lib.BookImageInsertAction;
import book.action.lib.BookBrwListAction;
import book.action.lib.BookInsertProAction;
//import book.action.lib.BookInfoAction;
import book.action.lib.BookListAction;
import book.action.lib.BookModifyStateAction;
import book.action.lib.BookOutBrwListAction;
import book.action.lib.BookOutRevListAction;
import book.action.lib.BookRevListAction;
import vo.admin.ActionForward;
import vo.admin.Action;
import vo.admin.ActionVoid;

@WebServlet("*.bookL")
public class BookController extends javax.servlet.http.HttpServlet 
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
		if(command.equals("/BookList.bookL")){ //도서 리스트
			action = new BookListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BookRevList.bookL")){ //도서 예약내역
			action = new BookRevListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BookBrwList.bookL")){ //도서 대출내역
			action = new BookBrwListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BookOutRevList.bookL")){ //도서 관외예약내역
			action = new BookOutRevListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BookOutBrwList.bookL")){ //도서 관외대출내역
			action = new BookOutBrwListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BookInsertForm.bookL")){ //도서 입력 폼
			request.setAttribute("pagefile", "/book/lib_bookInsertForm.jsp");
			forward = new ActionForward();
			forward.setPath("lib_template.jsp");
		} else if(command.equals("/BookInsert.bookL")){ //도서 입력 프로세스
			action = new BookInsertProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BookModifyState.bookL")) { //도서상태 변경(0 -> 3 or 3 -> 0)
			action = new BookModifyStateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
//		else if(command.equals("/BookInfo.bookA")){ //도서 상세
////			action = new BookInfoAction();
//			action2 = new BookInfoAction();
//			try {
//				action2.execute(request, response);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			object = new BookInfoAction();
////			try {
////				forward = action.execute(request, response);
////			} catch (Exception e) {
////				e.printStackTrace();
////			}
//		} else if(command.equals("/BookImageInsert.bookA")) { //도서 이미지 가져오기(ajax)
//			action = new BookImageInsertAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else if(command.equals("/BookModifyState.bookA")) { //도서상태 변경(0 -> 3 or 3 -> 0)
//			action = new BookModifyStateAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
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