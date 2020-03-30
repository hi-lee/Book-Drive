package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.BoardDeleteProSvc;
import board.svc.admin.BoardDeleteProService;
import action.Action;
import action.ActionForward;

public class BoardDeleteProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String boardNum = request.getParameter("boardnum");
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		String flag = request.getParameter("flag");
		BoardDeleteProSvc boardDeleteProService = new BoardDeleteProSvc();
		boolean isDeleteCheck = boardDeleteProService.DeleteBoard(boardNum);
		if (!isDeleteCheck) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 삭제에 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			request.setAttribute("isdelete", true);
			forward = new ActionForward();
			if (flag.equals("4")) {
				forward.setPath("freeBoard.bo?page="+page);
			} else if (flag.equals("3")) {
				forward.setPath("wishBoard.bo?page="+page);
			} else if (flag.equals("2")) {
				forward.setPath("qnaBoard.bo?page="+page);
			}
		}
		return forward;
	}

}