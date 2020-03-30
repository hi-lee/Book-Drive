package book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import book.svc.BookDetailSvc;
import book.vo.Book;
import book.vo.RentalIdv;

public class BookInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String info_num = request.getParameter("bookNum");
		String page = request.getParameter("page");
		String search = request.getParameter("search");
		String value = request.getParameter("value");
//		String id = request.getParameter("id");
		BookDetailSvc bookDetailSvc = new BookDetailSvc();
		
		Book book = bookDetailSvc.getBook(info_num);
		RentalIdv rentalIdv = bookDetailSvc.getRental(info_num);
		
		request.setAttribute("page", page);
		request.setAttribute("book", book);
		request.setAttribute("rentalIdv", rentalIdv);
		request.setAttribute("pageIn", "/book/bookInfo.jsp");
		forward = new ActionForward();
		forward.setPath("template_sub.jsp");	
		return forward;
	}

}
