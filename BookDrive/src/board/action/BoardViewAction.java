package board.action;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import board.svc.BoardDetailSvc;
import board.vo.Board;

public class BoardViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		ActionForward forward = null;
		
		BoardDetailSvc boardDetailsvc = new BoardDetailSvc();
		Board board = boardDetailsvc.getBoard(boardNum);
		Board prevBoard = boardDetailsvc.getBoardNum(boardNum, "prev"); //이전글
		Board nextBoard = boardDetailsvc.getBoardNum(boardNum, "next"); //다음글
		forward = new ActionForward();
		request.setAttribute("page", page);
		request.setAttribute("board", board);
		request.setAttribute("prevBoard", prevBoard);
		request.setAttribute("nextBoard", nextBoard);
		request.setAttribute("pageIn", "/board/boardDetail.jsp");
		forward.setPath("template_sub.jsp");
		
		return forward;
	}

}
