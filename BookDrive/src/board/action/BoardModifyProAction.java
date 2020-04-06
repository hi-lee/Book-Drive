package board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.svc.BoardModifyProSvc;
import board.vo.Board;
import action.Action;
import action.ActionForward;

public class BoardModifyProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		Board boardBean = null;
		String realFolder = "";
		String saveFolder = "/board/boardUpload";
		String fileName = "";
		int fileSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
//		int adminNum = multi.getParameter("index") != null ? Integer.parseInt(multi.getParameter("index")) : 0;
		int boardNum = multi.getParameter("boardnum") != null ? Integer.parseInt(multi.getParameter("boardnum")) : 0;
		int page = multi.getParameter("page") != null ? Integer.parseInt(multi.getParameter("page")) : 1;
		String boardFlag = multi.getParameter("boardflag") != null ? multi.getParameter("boardflag") : "1";
		boardBean = new Board();
		boardBean.setBoardNum(boardNum);
		boardBean.setBoardSubject(multi.getParameter("subject"));
		boardBean.setBoardPass(multi.getParameter("pass"));
		boardBean.setBoardContent(multi.getParameter("content"));
		BoardModifyProSvc boardModifyProSvc = new BoardModifyProSvc();
		boolean isUpdateCheck = boardModifyProSvc.updateBoard(boardBean);
		
		if (!isUpdateCheck) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정에 실패했습니다. 다시 확인해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		} else { //QnA는 Detail로 이동. 공지,희망,자유는 수정으로 이동
			forward = new ActionForward();
			HttpSession session = request.getSession();
			String index = (String) session.getAttribute("userIndex");
			System.out.println(boardFlag);
			if (boardFlag.equals("4")) forward.setPath("boardDetail.bo?boardNum=" + boardNum + "&page=" + page + "&index=" + index); //자유게시판
			if (boardFlag.equals("3")) forward.setPath("wishboardDetail.bo?boardNum=" + boardNum + "&page=" + page + "&index=" + index); //도서이용신청
			if (boardFlag.equals("2")) forward.setPath("qnaboardDetail.bo?boardNum=" + boardNum + "&page=" + page + "&index=" + index); //내 문의사항
		}
		return forward;
	}
}
