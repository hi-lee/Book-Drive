package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.action.BoardDeleteProAction;
import board.action.BoardFreeListAction;
import board.action.BoardModifyFormAction;
import board.action.BoardModifyProAction;
import board.action.BoardNoticeListAction;
import board.action.BoardNoticeViewAction;
import board.action.BoardQnAListAction;
import board.action.BoardQnAViewAction;
import board.action.BoardViewAction;
import board.action.BoardWishListAction;
import board.action.BoardWishViewAction;
import board.action.BoardWriteProAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.bo")
public class BoardController extends HttpServlet {

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		System.out.println("boardController  : " + command);

		if (command.equals("/freeBoard.bo")) { //자유게시판 리스트
			action = new BoardFreeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/boardDetail.bo")) { //자유게시판 상세보기
			action = new BoardViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/freeBoardWriteForm.bo")) { //게시판 작성 Form
			request.setAttribute("pageIn", "/board/boardWrite.jsp");
			forward = new ActionForward();
			forward.setPath("template_sub.jsp");
		} else if(command.equals("/freeBoardWritePro.bo")){ //게시판 작성 Process
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardModifyForm.bo")){ //수정 Form
			action = new BoardModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardModifyPro.bo")){ //수정 Process
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDeletePro.bo")){ //삭제 Process
			action = new BoardDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeBoard.bo")) { //공지사항 리스트
			action = new BoardNoticeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/noticeboardDetail.bo")) { //공지사항 상세보기
			action = new BoardNoticeViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/wishBoard.bo")) { //도서이용신청 리스트
			action = new BoardWishListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/wishboardDetail.bo")) { //도서이용신청 상세보기
			action = new BoardWishViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/wishBoardWriteForm.bo")) { //도서이용신청 작성 Form
			request.setAttribute("pageIn", "/board/boardWrite.jsp");
			forward = new ActionForward();
			forward.setPath("template_sub.jsp");
		} else if (command.equals("/wishBoardWritePro.bo")) { //도서이용신청 작성 Process
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaBoard.bo")) { //문의사항 리스트
			action = new BoardQnAListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaboardDetail.bo")) { //문의사항 상세보기
			action = new BoardQnAViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/qnaBoardWriteForm.bo")) { //문의사항 작성 Form
			request.setAttribute("pageIn", "/board/boardWrite.jsp");
			forward = new ActionForward();
			forward.setPath("template_sub.jsp");
		} else if (command.equals("/qnaBoardWritePro.bo")) { //문의사항 작성 Process
			action = new BoardWriteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BoardController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

}
