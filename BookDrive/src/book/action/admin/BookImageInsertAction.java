package book.action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.admin.BookImageInsertService;
import vo.admin.Action;
import vo.admin.ActionForward;

public class BookImageInsertAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		BookImageInsertService bookImageInsertService = new BookImageInsertService();
		bookImageInsertService.selectISBN();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('정보 얻어옴')");
		out.println("history.back();");
		out.println("</script>");
		return forward;
	}

}
