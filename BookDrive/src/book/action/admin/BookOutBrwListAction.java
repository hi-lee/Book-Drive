package book.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.admin.BookOutBrwListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BookRental;

public class BookOutBrwListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<BookRental> bookBrwList = new ArrayList<BookRental>();
		BookOutBrwListService bookOutBrwListService = new BookOutBrwListService();
		bookBrwList = bookOutBrwListService.selectBookBrwList();
		
		request.setAttribute("pagefile", "/book/admin_bookOutBrwList.jsp");
		request.setAttribute("bookBrwList", bookBrwList);
//		request.setAttribute("libNameList", libNameList);
		
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}
}
