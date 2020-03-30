package board.action.admin;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.svc.admin.BoardNoticeWriteProService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;

public class BoardWriteProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		BoardBean boardBean = null;
		int boardNum = 0; //글 등록 성공히 완료된 boardnum 번호를 가져와서 담는 변수
		String realFolder = "";
		String saveFolder = "/board/boardUpload";
		String fileName = "";
		int fileSize = 5 * 1024 * 1024;
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		MultipartRequest multi = new MultipartRequest(request, realFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		int adminNum = multi.getParameter("index") != null ? Integer.parseInt(multi.getParameter("index")) : 0;
		boardBean = new BoardBean();
		boardBean.setAdminNum(adminNum);
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
		BoardNoticeWriteProService boardNoticeWriteProService = new BoardNoticeWriteProService();
		boardNum = boardNoticeWriteProService.selectBoardNotice(boardBean);
		
		if (boardNum <= 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('글 작성에 실패했습니다. 다시 확인해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		} else { //글 작성 완료되면 수정Form으로 바로 이동한다.
			forward = new ActionForward();
			if (boardBean.getBoardFlag().equals("2")) { //QnA는 디테일로 들어간다.
				forward.setPath("BoardQnADetail.boardA?boardnum=" + boardNum + "&page=1");
			} else {
				forward.setPath("BoardModifyForm.boardA?boardnum=" + boardNum + "&page=1");
			}
		}
		return forward;
	}
}