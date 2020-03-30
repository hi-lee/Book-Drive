package board.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.svc.BoardWriteSvc;
import board.vo.Board;
import action.Action;
import action.ActionForward;

public class BoardWriteProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		Board boardBean = null;
		int boardNum = 0; //글 등록 성공히 완료된 boardnum 번호를 가져와서 담는 변수
		String realFolder = "";
		String saveFolder = "/board/boardUpload";
		String fileName = "";
		int fileSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		int memIndex = multi.getParameter("index") != null ? Integer.parseInt(multi.getParameter("index")) : 0;
		boardBean = new Board();
		boardBean.setMemIndex(memIndex);
		boardBean.setBoardSubject(multi.getParameter("subject"));
		boardBean.setBoardPass(multi.getParameter("pass"));
		boardBean.setBoardWriter(multi.getParameter("writer"));
		boardBean.setBoardContent(multi.getParameter("content"));
		boardBean.setBoardFlag(multi.getParameter("boardgubun"));
		boardBean.setBoardWriterFlag("0");
		if (multi.getFilesystemName((String)multi.getFileNames().nextElement()) != null) {
			fileName = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
		}
		boardBean.setBoardFile(fileName);
		BoardWriteSvc boardWriteSvc = new BoardWriteSvc();
		boardNum = boardWriteSvc.insertBoard(boardBean);
		
		if (boardNum <= 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 작성에 실패했습니다. 다시 확인해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			if (boardBean.getBoardFlag().equals("3")) {
				forward.setPath("wishboardDetail.bo?boardNum=" + boardNum + "&page=1");
			} else if (boardBean.getBoardFlag().equals("4")) {
				forward.setPath("boardDetail.bo?boardNum=" + boardNum + "&page=1");
			} else if (boardBean.getBoardFlag().equals("2")) {
				forward.setPath("qnaboardDetail.bo?boardNum=" + boardNum + "&page=1");
			}
		}
		return forward;
	}
}