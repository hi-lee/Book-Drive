package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.svc.BoardDetailSvc;
import board.vo.Board;

public class BoardWishViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		ActionForward forward = null;
		
		BoardDetailSvc boardDetailsvc = new BoardDetailSvc();
		Board board = boardDetailsvc.getBoard(boardNum);
		
		forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("board", board);
		request.setAttribute("pageIn", "/board/boardWishDetail.jsp");
		forward.setPath("template_sub.jsp");
		
		return forward;
	}

}
