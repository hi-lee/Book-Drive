package board.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.admin.BoardDetailService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;

public class BoardQnADetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		int boardNum = request.getParameter("boardnum") != null ? Integer.parseInt(request.getParameter("boardnum")) : 0;
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 0;
		BoardBean boardBean_parents = new BoardBean();
		BoardBean boardBean_child = new BoardBean();
		
		BoardDetailService boardDetailService = new BoardDetailService();
		boardBean_parents = boardDetailService.selectBoardDetail(boardNum);
		boardBean_child = boardDetailService.selectBoardReplyDetail(boardNum);
		
		request.setAttribute("page", page);
		request.setAttribute("pagefile", "/board/admin_boardQnADetail.jsp");
		request.setAttribute("boardBean_p", boardBean_parents);
		request.setAttribute("boardBean_c", boardBean_child);
		
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}
}