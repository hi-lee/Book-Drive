package member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import book.vo.RentalIdv;
import member.svc.MyRentalListSvc;

public class myRentalListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String list_id = request.getParameter("id");
		String memIndex = "";
		if (!(request.getParameter("index") == null || request.getParameter("index").trim().equals(""))) {
			memIndex = request.getParameter("index");
		}

		if (session.getAttribute("id") == null || list_id == null || list_id.equals("") || memIndex.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('대출/예약내역-로그인하세요.');");
			out.print("location.href='loginForm.log';");
			out.print("</script>");
		}
		MyRentalListSvc myBookListSvc = new MyRentalListSvc();
		ArrayList<RentalIdv> rentalList = myBookListSvc.getRentalBookList(memIndex);
		request.setAttribute("rentalList", rentalList);
		request.setAttribute("pageIn", "/member/myRentalList.jsp");
		forward = new ActionForward();
		forward.setPath("/template_sub.jsp");
		return forward;
	}

}
