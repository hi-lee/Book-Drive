package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.BoardDetailSvc;
import board.svc.admin.BoardDetailService;
import board.vo.Board;
import action.Action;
import action.ActionForward;
import vo.admin.BoardBean;

public class BoardQnAViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		int boardNum = request.getParameter("boardNum") != null ? Integer.parseInt(request.getParameter("boardNum")) : 0;
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 0;
		Board boardBean_parents = new Board();
		Board boardBean_child = new Board();
		
		BoardDetailSvc boardDetailSvc = new BoardDetailSvc();
		boardBean_parents = boardDetailSvc.getBoard(boardNum);
		boardBean_child = boardDetailSvc.selectBoardReplyDetail(boardNum);
		
		request.setAttribute("page", page);
		request.setAttribute("pageIn", "/board/boardQnADetail.jsp");
		request.setAttribute("boardBean_p", boardBean_parents);
		request.setAttribute("boardBean_c", boardBean_child);
		
		forward = new ActionForward();
		forward.setPath("template_sub.jsp");
		return forward;
	}
}