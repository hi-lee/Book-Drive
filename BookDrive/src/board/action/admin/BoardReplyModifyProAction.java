package board.action.admin;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.svc.admin.BoardModifyProService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;

public class BoardReplyModifyProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		BoardBean boardBean = null;
		String realFolder = "";
		String saveFolder = "/board/boardUpload";
		String fileName = "";
		int fileSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
//		int adminNum = multi.getParameter("index") != null ? Integer.parseInt(multi.getParameter("index")) : 0;
		int boardNum_p = multi.getParameter("p_boardnum") != null ? Integer.parseInt(multi.getParameter("p_boardnum")) : 0;
		int boardNum = multi.getParameter("boardnum") != null ? Integer.parseInt(multi.getParameter("boardnum")) : 0;
		int page = multi.getParameter("page") != null ? Integer.parseInt(multi.getParameter("page")) : 1;
		boardBean = new BoardBean();
//		boardBean.setAdminNum(adminNum);
		boardBean.setBoardNum(boardNum);
		boardBean.setBoardSubject(multi.getParameter("subject"));
		boardBean.setBoardPass(multi.getParameter("pass"));
//		boardBean.setBoardWriter(multi.getParameter("writer"));
		boardBean.setBoardContent(multi.getParameter("content"));
//		boardBean.setBoardFlag(multi.getParameter("boardgubun"));
//		boardBean.setBoardWriterFlag("0");
//		if (multi.getFilesystemName((String)multi.getFileNames().nextElement()) != null) {
//			fileName = multi.getOriginalFileName((String)multi.getFileNames().nextElement());
//		}
//		boardBean.setBoardFile(fileName);
//		BoardNoticeWriteProService boardNoticeWriteProService = new BoardNoticeWriteProService();
//		boardNum = boardNoticeWriteProService.selectBoardNotice(boardBean);
		BoardModifyProService boardModifyProAction = new BoardModifyProService();
		boolean isUpdateCheck = boardModifyProAction.updateBoard(boardBean);
		
		if (!isUpdateCheck) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 수정에 실패했습니다. 다시 확인해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		} else {
//			request.setAttribute("pagefile", "board/admin_boardNoticeDetail.jsp");
			forward = new ActionForward();
//			forward.setPath("BoardModifyForm.boardA?boardnum=" + boardNum + "&page=" + page);
			forward.setPath("BoardQnADetail.boardA?boardnum=" + boardNum_p + "&page=" + page);
//			if (boardBean.getBoardFlag().equals("1")) {
//				forward.setPath("BoardNoticeDetail.boardA?boardnum=" + boardNum);
//			} else {
//				forward.setPath("BoardQnADetail.boardA?boardnum=" + boardNum);
//			}
		}
		return forward;
	}

}
