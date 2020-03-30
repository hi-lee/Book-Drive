package book.action.lib;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.lib.BookBrwListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BookRental;

public class BookBrwListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<BookRental> bookBrwList = new ArrayList<BookRental>();
		String libCode = request.getParameter("libcode") != null ? request.getParameter("libcode") : "";
		BookBrwListService bookBrwListService = new BookBrwListService();
		bookBrwList = bookBrwListService.selectBookBrwList(libCode);
		
		request.setAttribute("pagefile", "/book/lib_bookBrwList.jsp");
		request.setAttribute("bookBrwList", bookBrwList);
//		request.setAttribute("libNameList", libNameList);
		
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}
}
