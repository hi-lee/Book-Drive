package board.action.lib;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.svc.admin.BoardNoticeWriteProService;
import board.svc.lib.BoardRepleWriteProService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;

public class BoardRepleWriteProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//QnA 전용 답글..
		ActionForward forward = null;
		BoardBean boardBean = new BoardBean();
		int boardNum = request.getParameter("boardnum") != null ? Integer.parseInt(request.getParameter("boardnum")) : 0;
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 0;
		boardBean.setAdminNum(Integer.parseInt(request.getParameter("index")));
		boardBean.setBoardSubject(request.getParameter("subject"));
		boardBean.setBoardPass(request.getParameter("pass"));
		boardBean.setBoardWriter(request.getParameter("writer"));
		boardBean.setBoardContent(request.getParameter("content"));
		boardBean.setBoardFlag(request.getParameter("boardflag"));
		boardBean.setBoardWriterFlag("2");
		boardBean.setBoardRef(request.getParameter("ref"));
		boardBean.setBoardLev(request.getParameter("lev"));
		boardBean.setBoardSeq(request.getParameter("seq"));
		boardBean.setBoardFile("");
		BoardRepleWriteProService boardReplyWriteProService = new BoardRepleWriteProService();
		boolean isReplySuccess = boardReplyWriteProService.insertReply(boardBean, boardNum);
		if (!isReplySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('답글 작성에 실패했습니다. 다시 확인해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		} else {
//			request.setAttribute("pagefile", "BoardNoticeDetail.boardA?boardnum="+boardNum+"&page="+page);
			forward = new ActionForward();
			if (boardBean.getBoardFlag().equals("1")) {
				forward.setPath("BoardNoticeDetail.boardA?boardnum="+boardNum+"&page="+page);
			} else if (boardBean.getBoardFlag().equals("2")) {
				forward.setPath("BoardQnADetail.boardL?boardnum="+boardNum+"&page="+page);
			}
		}
		return forward;
	}
}