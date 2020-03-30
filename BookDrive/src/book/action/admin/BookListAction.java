package book.action.admin;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.admin.BookListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Book;
import vo.admin.PageInfo;

public class BookListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		ArrayList<Book> bookList = new ArrayList<Book>(); //책정보
		ArrayList<Hashtable<String, String>> libNameList = new ArrayList<>(); //도서관코드(selectBox)
		ArrayList<String> bookStateList = new ArrayList<>(); //책 상태플래그(selectBox)
		String stateName[] = {"대출가능", "대출중", "예약중", "관외보유", "관외대출", "관외예약", "대출+예약", "대출+관외예약", "관외대출+관외예약"};
		String libCode = request.getParameter("libcode") != null ? request.getParameter("libcode") : "";
		String bookState = request.getParameter("bookstate") != null ? request.getParameter("bookstate") : "";
		if (!bookState.equals("")) { //jsp의 status.index가 0은 빈값으로 인식하기 때문에 status.index+1 처리를 하였기 때문에 java에서 -1 해줘야함
			bookState = String.valueOf((Integer.parseInt(bookState) - 1));
		}
		String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";
		System.out.println("bookState : " + bookState);
		int nowPage = 1; //디폴트(첫페이지)
		int limit = 10; //페이지에 보여줄 목록 수
		int limitPage=10; //페이지 수
		
		if (request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		
		BookListService bookListService = new BookListService();
		int listCount = bookListService.getListCount(libCode, keyword, bookState); //총 리스트 수를 받아옴 (총 글 갯수)
		libNameList = bookListService.selectLibNameList();
		bookList = bookListService.selectBookList(nowPage, limit, libCode, keyword, bookState);
		for (int i = 0; i < 8; i++) {
			bookStateList.add(stateName[i]);
		}
		int maxPage = (int) ((double) listCount / limit + 0.95); //총 페이지 수, 0.95를 더해서 올림 처리
		int startPage = (((int) ((double) nowPage / limitPage + 0.9)) -1) * limitPage + 1; //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등등)
		int endPage = startPage + limitPage - 1;
		if (endPage > maxPage) endPage = maxPage;
		System.out.println("max : " + maxPage + ", start : " + startPage + ", end : " + endPage);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setnowPage(nowPage);
		pageInfo.setStartPage(startPage);
		
		request.setAttribute("pagefile", "/book/admin_bookList.jsp?libcode="+libCode+"&keyword="+keyword);
		request.setAttribute("pageinfo", pageInfo);
		request.setAttribute("bookList", bookList);
		request.setAttribute("libNameList", libNameList);
		request.setAttribute("bookStateList", bookStateList);
		
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}

}
