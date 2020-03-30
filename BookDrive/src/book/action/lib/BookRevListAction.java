package book.action.lib;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.lib.BookRevListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BookRental;

public class BookRevListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<BookRental> bookRevList = new ArrayList<BookRental>();
		String libCode = request.getParameter("libcode") != null ? request.getParameter("libcode") : "";
		BookRevListService bookRevListService = new BookRevListService();
		bookRevList = bookRevListService.selectBookRevList(libCode);
		
		request.setAttribute("pagefile", "/book/lib_bookRevList.jsp");
		request.setAttribute("bookRevList", bookRevList);
//		request.setAttribute("libNameList", libNameList);
		
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}
}