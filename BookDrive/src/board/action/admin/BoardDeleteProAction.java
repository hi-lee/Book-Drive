package board.action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.admin.BoardDeleteProService;
import vo.admin.Action;
import vo.admin.ActionForward;

public class BoardDeleteProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String boardNum = request.getParameter("boardnum");
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		String flag = request.getParameter("flag");
		BoardDeleteProService boardDeleteProService = new BoardDeleteProService();
		boolean isDeleteCheck = boardDeleteProService.DeleteBoard(boardNum);
		if (flag.equals("2")) { //QnA
			if (!request.getParameter("cboardnum").equals("null")) { //답글 존재시
				boolean isDeleteReplyCheck = boardDeleteProService.deleteReplyBoard(request.getParameter("cboardnum"));
			} else { //답글 미존재시
				System.out.println("널값이다");
			}
		}
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
			forward.setPath("BoardList"+flag+".boardA?page="+page);
		}
		return forward;
	}

}