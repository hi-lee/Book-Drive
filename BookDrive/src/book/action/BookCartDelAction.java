package book.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import book.svc.BookCartDelSvc;
import book.svc.BookCartListSvc;
import book.vo.Cart;

public class BookCartDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		boolean isDelSuccess = false;
		String memIndex = "";
		String[] cartNum = null;

		if (request.getParameter("index") != null) {
			memIndex = request.getParameter("index");
//			System.out.println("index:   "+ memIndex);
		}
//		삭제버튼으로 체크박스가 선택되어 넘어온 경우, 각 체크박스의 값을 cartNum으로 설정.
//		바구니비우기로 submit된 경우: submit과 동시에 모든 체크박스 선택됨.
		if (request.getParameterValues("data") != null) {
			cartNum = request.getParameterValues("data");
			for (int i = 0; i < cartNum.length; i++) {
//				System.out.println("data  : " + cartNum[i]);
			}
		} 

		BookCartDelSvc bookCartDelSvc = new BookCartDelSvc();
		isDelSuccess = bookCartDelSvc.delCart(memIndex, cartNum);

		if (!isDelSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('보관함 삭제실패');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			BookCartListSvc bookCartListSvc = new BookCartListSvc();
			ArrayList<Cart> cartList = bookCartListSvc.getCartList(memIndex);
			request.setAttribute("cartList", cartList);
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('보관함에서 삭제되었습니다.');");
			out.print("location.href='bookCartList.bk?index=" + memIndex + "';");
			out.print("</script>");
		}
		return forward;
	}

}
