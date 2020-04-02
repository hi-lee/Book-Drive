package book.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import book.svc.BookCartListSvc;
import book.svc.BookListSvc;
import book.vo.Book;
import book.vo.Cart;
import book.vo.SearchHistory;

public class BookSearchProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;

		String search = "bookName";
		String value = "";
		String usage = null; // 관외확인
		String category = ""; // 검색히스토리 카테고리
		int limit = 0;
		String memIndex = "";

		if (request.getParameter("index") != null) {
			memIndex = request.getParameter("index");
		}
		if (request.getParameter("search") != null && !request.getParameter("search").trim().equals("")) {
//			DriveList: not null이 빈문자가 아니면 getParameter, or 조건이면 Err뜸 
			search = request.getParameter("search");
		}
		if (request.getParameter("value") != null) {
			value = request.getParameter("value");
		}

		if (request.getParameter("usage") != null && !request.getParameter("usage").trim().equals("")) {
			usage = request.getParameter("usage");
		}
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

		if (usage != null && !usage.trim().equals("")) {
			limit = 8;
		} else {
			limit = 5;
		}
		int limitPage = 10;

		BookListSvc bookListSvc = new BookListSvc();
		int listCount = bookListSvc.getListCount(search, value, usage);

		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = (((int) ((double) page / limitPage + 0.9)) - 1) * limitPage + 1;
		int endPage = startPage + limitPage - 1;
		if (endPage > maxPage)
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

//		북리스트 - 보관함 책 개수 표시 
		if (memIndex != null) {
			BookCartListSvc bookCartListSvc = new BookCartListSvc();
			ArrayList<Cart> cartList = bookCartListSvc.getCartList(memIndex);
			request.setAttribute("cartList", cartList);
		}

//		검색히스토리 리스트 생성 
		HttpSession session = request.getSession();
		HashMap<String, SearchHistory> searchList = (HashMap<String, SearchHistory>) session.getAttribute("searchList");
		if(searchList == null ) {
			searchList = new HashMap<String, SearchHistory>();
		}
		if (value != null && !value.equals("")) {
			SearchHistory history = new SearchHistory();
			history.setSearch(search);
			history.setValue(value);
			searchList.put(search+value, history);
//			System.out.println("value : " + history.getValue());
		}
		
//		ArrayList<SearchHistory> searchList = (ArrayList<SearchHistory>) session.getAttribute("searchList");
//		if (searchList == null && value != null && !value.equals("")) { 
//			searchList = new ArrayList<>();
//		}
//		if (value != null && !value.equals("")) {
//			SearchHistory history = null;
//			history = new SearchHistory();
//			history.setSearch(search);
//			history.setValue(value);
//			history.setValue(value);
//			searchList.add(history);
//
//			System.out.println("value : " + history.getValue());
//		}
		session.setAttribute("searchList", searchList);

		if (usage != null && !usage.trim().equals("")) {
			request.setAttribute("pageIn", "book/bookDriveList.jsp");
		} else {
			request.setAttribute("pageIn", "book/bookList.jsp");
		}

		forward = new ActionForward();
		forward.setPath("template_sub.jsp");
		return forward;
	}

}
