package board.action.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.lib.BoardDetailService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;

public class BoardRepleWriteFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		String nowPage = request.getParameter("page");
		int boardNum = Integer.parseInt(request.getParameter("boardnum"));
		BoardDetailService boardDetailService = new BoardDetailService();
		BoardBean boardBean = boardDetailService.getBoardBean(boardNum);
		request.setAttribute("boardBean", boardBean);
		request.setAttribute("page", nowPage);
		request.setAttribute("pagefile", "/board/lib_boardRepleWriteForm.jsp");
		forward.setPath("lib_template.jsp");
		return forward;
	}

}
