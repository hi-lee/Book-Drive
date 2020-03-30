package book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import book.svc.BookRevSvc;
import book.vo.Book;
import member.vo.Member;

public class BookRevAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		if (session.getAttribute("userID")==null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('예약하려면 로그인을 하세요');");
			out.print("location.href='loginForm.log';");
			out.print("</script>");
		}
		boolean isModifySuccess = false;
	
		String memIndex =request.getParameter("index");
		String id = request.getParameter("userID");
		String libCode = request.getParameter("libCode");
		String bookNum = request.getParameter("bookNum");
		String state = request.getParameter("state");
		String usage = null;
		
		if(request.getParameter("usage") != null) {
			usage = request.getParameter("usage");
		}
		String nowPage = request.getParameter("page");
		
		System.out.println("usage : " + usage + ",  state : " + state);
		ActionForward forward = null;
		BookRevSvc bookRevSvc = new BookRevSvc();
		isModifySuccess = bookRevSvc.insertState(memIndex, bookNum, libCode, state, usage);

		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('예약실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('예약되었습니다.');");
			out.print("location.href='bookInfo.bk?page=" + nowPage + "&bookNum=" + bookNum + "&search=" + "';");
			out.print("</script>");
//			session.setAttribute("userID", id);
//			request.setAttribute("page", nowPage);
//			request.setAttribute("pageIn", "/book/bookInfo.jsp");
//			request.setAttribute("pageIn", "bookInfo.bk?page="+nowPage+"&bookNum="+bookNum);
//			forward = new ActionForward();
//			forward.setPath("template_sub.jsp");
		}
		return forward;
	}

}
