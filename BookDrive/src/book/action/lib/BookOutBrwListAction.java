package book.action.lib;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.lib.BookBrwListService;
import book.svc.lib.BookOutBrwListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BookRental;

public class BookOutBrwListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<BookRental> bookBrwList = new ArrayList<BookRental>();
		String libCode = request.getParameter("libcode") != null ? request.getParameter("libcode") : "";
		BookOutBrwListService bookOutBrwListService = new BookOutBrwListService();
		bookBrwList = bookOutBrwListService.selectBookBrwList(libCode);
		
		request.setAttribute("pagefile", "/book/lib_bookOutBrwList.jsp");
		request.setAttribute("bookBrwList", bookBrwList);
//		request.setAttribute("libNameList", libNameList);
		
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}
}
