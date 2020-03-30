package book.action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.svc.admin.BookModifyStateService;
import vo.admin.Action;
import vo.admin.ActionForward;

public class BookModifyStateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String libCode = request.getParameter("libcode") != null ? request.getParameter("libcode") : "";
		String bookState = request.getParameter("bookstate") != null ? request.getParameter("bookstate") : "";
		if (!bookState.equals("")) { //jsp의 status.index가 0은 빈값으로 인식하기 때문에 status.index+1 처리를 하였기 때문에 java에서 -1 해줘야함
			bookState = String.valueOf((Integer.parseInt(bookState) - 1));
		}
		String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : "";
		String bookNum = request.getParameter("num");
		String updateState = request.getParameter("updatestate");
		
		BookModifyStateService bookModifyStateService = new BookModifyStateService();
		boolean updateCheck = bookModifyStateService.updateBookState(bookNum, updateState);
		
		if (!updateCheck) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('정보수정에 실패하였습니다.)");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("BookList.bookA?libcode=" + libCode + "&bookstate=" + bookState + "&keyword=" + keyword);
		}
		return forward;
	}

}
