package board.action.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.lib.BoardDetailService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;

public class BoardNoticeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		int boardNum = request.getParameter("boardnum") != null ? Integer.parseInt(request.getParameter("boardnum")) : 0;
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 0;
		BoardBean boardBean = new BoardBean();
		
		BoardDetailService boardDetailService = new BoardDetailService();
		boardBean = boardDetailService.selectBoardDetail(boardNum);
		
		request.setAttribute("page", page);
		request.setAttribute("pagefile", "/board/lib_boardNoticeDetail.jsp");
		request.setAttribute("boardBean", boardBean);
		
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}
}