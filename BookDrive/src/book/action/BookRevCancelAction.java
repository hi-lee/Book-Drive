package book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import book.svc.BookRevCancelSvc;

public class BookRevCancelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		boolean isModifySuccess = false;
		
		if(session.getAttribute("userID") == null ) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('예약취소는 로그인하세요');");
			out.print("location.href='loginForm.log';");
			out.print("</script>");
		}
		
		int memIndex = Integer.parseInt(request.getParameter("index"));
		String id = request.getParameter("id");
		String bookNum = request.getParameter("bookNum");
		String state = request.getParameter("state");
		
		BookRevCancelSvc bookRevCancelSvc = new BookRevCancelSvc();
		isModifySuccess = bookRevCancelSvc.cancelState(memIndex, bookNum, state);
		
		if(!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('예약취소 실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('예약취소되었습니다. ');");
			out.print("location.href='myRentalList.mem?id=" + id + "&index=" + memIndex +"';");
			out.print("</script>");
		}
		return forward;
	}

}
