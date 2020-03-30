package book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import book.svc.BookReturnSvc;
import book.svc.BookRevSvc;

public class BookReturnAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		boolean isModifySuccess = false;
		String usage = null;
		if (request.getParameter("usage") != null && !request.getParameter("usage").trim().equals("")) {
			usage = request.getParameter("usage");
		}
		int memIndex = 0;
		String id = "";
		String state = "";

		if (usage == null) {
			if (session.getAttribute("userID") == null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('반납하려면 로그인하세요');");
				out.print("location.href='loginForm.log';");
				out.print("</script>");
			}
		}
		
		if (request.getParameter("index") != null) {
			memIndex = Integer.parseInt(request.getParameter("index"));
		}
		if (request.getParameter("id") != null) {
			id = request.getParameter("id");
		}
		if (request.getParameter("id") != null) {
			state = request.getParameter("state");
		}

//		String libCode = request.getParameter("libCode");
		String bookNum = request.getParameter("bookNum");
		String renNum = request.getParameter("renNum");
//		String state = request.getParameter("state");
//		String nowPage = request.getParameter("page");
//		String search = request.getParameter("search");
//		String value = request.getParameter("value");

		BookReturnSvc bookReturnSvc = new BookReturnSvc();
		isModifySuccess = bookReturnSvc.returnState(bookNum, usage, state, renNum);

	 System.out.println("returnAction usage : " + usage);
		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('반납실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			if (usage != null) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('반납 되었습니다.');");
				out.print("window.close()");
				out.print("</script>");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('반납 되었습니다.');");
				out.print("location.href='myRentalList.mem?id=" + id + "&index=" + memIndex + "';");
				out.print("</script>");
			}
		}
		return forward;
	}

}
