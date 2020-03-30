package book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import action.ActionVoid;
import book.svc.BookCartAddSvc;
import book.svc.BookDetailSvc;
import book.vo.Book;

public class BookCartAddAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String memIndex = "";
		String bookNum = "";
		boolean isInsertSuccess = false;

		if (request.getParameter("userIndex") != null) {
			memIndex = request.getParameter("userIndex");
		}
		if (request.getParameter("bookNum") != null) {
			bookNum = request.getParameter("bookNum");
		}

		if (memIndex != null && bookNum != null) {
			BookDetailSvc bookDetailSvc = new BookDetailSvc();
			BookCartAddSvc bookCartAddSvc = new BookCartAddSvc();

			Book bookCart = bookDetailSvc.getBook(bookNum);
			isInsertSuccess = bookCartAddSvc.addCart(memIndex, bookCart);

			System.out.println("userIndex :   " + memIndex + "  bookNum:   " + bookNum);
			System.out.println("bookCart : " + bookCart.getBookNum());

			if (!isInsertSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('담기 실패');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				JSONObject obj = new JSONObject();
				obj.put("result", "result");
				response.setContentType("application/x-json; charset=UTF-8");
				response.getWriter().print(obj);
			}
		} 
	}
}
