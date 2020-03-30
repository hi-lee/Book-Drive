package book.action.lib;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.lib.BookInsertProService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Book;

public class BookInsertProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		Book book = new Book();
		book.setLibCode(request.getParameter("libcode") != null ? request.getParameter("libcode") : "");
		book.setBookName(request.getParameter("bookname") != null ? request.getParameter("bookname") : "");
		book.setBookPub(request.getParameter("bookpub") != null ? request.getParameter("bookpub") : "");
		book.setBookPubDate(request.getParameter("bookpubdate") != null ? request.getParameter("bookpubdate") : "");
		book.setBookWriter(request.getParameter("bookwriter") != null ? request.getParameter("bookwriter") : "");
		book.setISBN(request.getParameter("isbn") != null ? request.getParameter("isbn") : "");
		book.setBookImage(request.getParameter("bookimage") != null ? request.getParameter("bookimage") : "");
		book.setBookCategory(request.getParameter("bookcategory") != null ? request.getParameter("bookcategory") : "000");
		
		BookInsertProService bookInsertProService = new BookInsertProService();
		boolean isInsertCheck = bookInsertProService.insertBook(book);
		
		if (!isInsertCheck) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('도서등록에 실패했습니다');");
			out.println("history.back();");
			out.println("</script>");
		} else {
//			forward = new ActionForward();
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('도서를 등록하였습니다');");
			out.println("location.href='BookInsertForm.bookL';");
			out.println("</script>");
		}
		return forward;
	}

}
