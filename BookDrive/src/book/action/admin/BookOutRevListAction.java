package book.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.admin.BookOutRevListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BookRental;

public class BookOutRevListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<BookRental> bookRevList = new ArrayList<BookRental>();
		BookOutRevListService bookOutRevListService = new BookOutRevListService();
		bookRevList = bookOutRevListService.selectBookRevList();
		
		request.setAttribute("pagefile", "/book/admin_bookOutRevList.jsp");
		request.setAttribute("bookRevList", bookRevList);
//		request.setAttribute("libNameList", libNameList);
		
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}
}