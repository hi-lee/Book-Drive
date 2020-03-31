package book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.ActionVoid;
import book.action.BookCartAddAction;
import book.action.BookCartDelAction;
import book.action.BookCartListAction;
import book.action.BookInfoAction;
import book.action.BookRentalAction;
import book.action.BookReturnAction;
import book.action.BookRevAction;
import book.action.BookRevCancelAction;
import book.action.BookSearchProAction;
import book.action.DriveRentalAction;

/**
 * Servlet implementation class BookController
 */
@WebServlet("*.bk")
public class BookController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		ActionVoid actionvoid = null;
		Action action = null;
		System.out.println("bkCtroller  :  " + command);

		if (command.equals("/bookSearch.bk")) {
			forward = new ActionForward();
			request.setAttribute("pageIn", "/book/search.jsp");
			forward.setPath("template_sub.jsp");
		} else if (command.equals("/bookSearchPro.bk")) {
			action = new BookSearchProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookInfo.bk")) {
			action = new BookInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookRental.bk")) {
			action = new BookRentalAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookRev.bk")) {
			action = new BookRevAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookReturn.bk")) {
			action = new BookReturnAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookRevCancel.bk")) {
			action = new BookRevCancelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/myBookCartAdd.bk")) {
			actionvoid = new BookCartAddAction();
			try {
				actionvoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookCartList.bk")) {
			action = new BookCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookCartDel.bk")) {
			action = new BookCartDelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/bookDriveMain.bk")) {
			forward = new ActionForward();
			request.setAttribute("pageIn", "/book/bookDriveMain.jsp");
			forward.setPath("template_sub.jsp");
		} else if (command.equals("/driveLogin.bk")) {
			forward = new ActionForward();
			request.setAttribute("pageIn", "/member/driveLogin.jsp");
			forward.setPath("template_sub.jsp");
		} else if (command.equals("/driveRental.bk")) {
			action = new DriveRentalAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/SearchHistory.bk")) {	
			System.out.println("aaaa");
			forward = new ActionForward();
			request.setAttribute("pageIn", "/book/SearchHistory.jsp");
			forward.setPath("template_sub.jsp");
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
	public BookController() {
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
