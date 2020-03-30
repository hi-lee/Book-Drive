package book.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import book.svc.BookCartListSvc;
import book.vo.Cart;

public class BookCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String memIndex = "";
		
		if (request.getParameter("index") != null) {
			memIndex = request.getParameter("index");
		}
		
		if (memIndex != null) {
			BookCartListSvc bookCartListSvc = new BookCartListSvc();
			ArrayList<Cart> cartList = bookCartListSvc.getCartList(memIndex);
			request.setAttribute("cartList", cartList);
		} else if (memIndex.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('보관함-로그인 하세요');");
			out.print("location.href='loginForm.log';");
			out.print("</script>");
		}
		
		request.setAttribute("pageIn", "book/bookCart.jsp");
		forward = new ActionForward();
		forward.setPath("template_sub.jsp");
		return forward;
	}
}
