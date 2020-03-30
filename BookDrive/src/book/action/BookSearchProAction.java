package book.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import book.svc.BookListSvc;
import book.vo.Book;

public class BookSearchProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;

		String search = "bookName";
		String value = "";
		String usage = null;
		int limit = 0;
		
		
		if (request.getParameter("search") != null && !request.getParameter("search").trim().equals("")) {
//			DriveList: not null이 빈문자가 아니면 getParameter, or 조건이면 Err뜸 
			search = request.getParameter("search");
		}
		if (request.getParameter("value") != null) {
			value = request.getParameter("value");
		}
		if(request.getParameter("usage") != null && !request.getParameter("usage").trim().equals("")) {
			usage = request.getParameter("usage");
		}
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		
		if(usage != null && !usage.trim().equals("")) {
			limit = 8;
		} else {
			limit = 5;
		}
		int limitPage = 10;
		
		System.out.println("search usage  :  "+usage);
		BookListSvc bookListSvc = new BookListSvc();
		int listCount = bookListSvc.getListCount(search, value, usage);

		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = (((int) ((double) page / limitPage + 0.9)) - 1) * limitPage + 1;
		int endPage = startPage + limitPage - 1;
		if(endPage > maxPage)
			endPage = maxPage;
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setPage(page);
		pageInfo.setStartPage(startPage);
		request.setAttribute("pageInfo", pageInfo);
		
		ArrayList<Book> bookList = bookListSvc.getBookList(page, limit, search, value, usage);
		request.setAttribute("bookList", bookList);
		
		if(usage != null && !usage.trim().equals("")) {
			request.setAttribute("pageIn", "book/bookDriveList.jsp");
		} else {
			request.setAttribute("pageIn", "book/bookList.jsp");		
		}
		
		forward = new ActionForward();
		forward.setPath("template_sub.jsp");
		return forward;
	}

}
