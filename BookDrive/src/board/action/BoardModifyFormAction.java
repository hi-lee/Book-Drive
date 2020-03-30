package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.BoardDetailSvc;
import board.svc.admin.BoardDetailService;
import board.vo.Board;
import action.Action;
import action.ActionForward;
import vo.admin.BoardBean;

public class BoardModifyFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		int boardNum = request.getParameter("boardnum") != null ? Integer.parseInt(request.getParameter("boardnum")) : 0;
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		Board boardBean = new Board();
		
		BoardDetailSvc boardDetailSvc = new BoardDetailSvc();
		boardBean = boardDetailSvc.getBoard(boardNum);
		
		request.setAttribute("page", page);
		request.setAttribute("pageIn", "/board/boardModify.jsp");
		request.setAttribute("boardBean", boardBean);
		
		forward = new ActionForward();
		forward.setPath("template_sub.jsp");
		return forward;
	}

}
