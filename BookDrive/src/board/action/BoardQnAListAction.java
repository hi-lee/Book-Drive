package board.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import board.svc.BoardListSvc;
import board.vo.Board;
import book.action.PageInfo;

public class BoardQnAListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String memIndex = "";
		if (!(request.getParameter("index") == null || request.getParameter("index").trim().equals(""))) {
			memIndex = request.getParameter("index");
		}
		System.out.println(session.getAttribute("userID"));
		System.out.println(memIndex);
		if (session.getAttribute("userID") == null || memIndex.equals("")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('나의 문의사항-로그인하세요.');");
			out.println("location.href='loginForm.log';");
			out.println("</script>");
		} else {
			String kind = request.getParameter("kind") != null ? request.getParameter("kind") : "title"; //제목, 작성자, 내용
			String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : ""; //검색값
			String os = request.getParameter("os") != null ? request.getParameter("os") : "desc"; //DESC, ASC
			String index = request.getParameter("index") != null ? request.getParameter("index") : ""; //로그인 인덱스번호
			String flag = "2"; //boardFlag
			ArrayList<Board> boardList = new ArrayList<Board>();
	
			int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
			int limit = request.getParameter("countPerPage") != null ? Integer.parseInt(request.getParameter("countPerPage")) : 10; //페이지에 보여줄 목록 수
			int limitPage = 10; //페이지 수
	
			BoardListSvc boardListSvc = new BoardListSvc();
			int listCount = boardListSvc.getListCount(kind, keyword, flag);
			boardList = boardListSvc.getBoardList(page, limit, kind, keyword, flag, os);
	
			int maxPage = (int) ((double) listCount / limit + 0.95);
			int startPage = (((int) ((double) page / limitPage + 0.95)) - 1) * limitPage + 1;
			int endPage = startPage + limitPage - 1;
			if (endPage > maxPage)
				endPage = maxPage;
			PageInfo pageInfo = new PageInfo();
			pageInfo.setEndPage(endPage);
			pageInfo.setListCount(listCount);
			pageInfo.setMaxPage(maxPage);
			pageInfo.setPage(page);
			pageInfo.setStartPage(startPage);
			
			request.setAttribute("pageinfo", pageInfo);
			request.setAttribute("boardList", boardList);
			
			forward = new ActionForward();
			request.setAttribute("pageIn", "/board/boardQnAList.jsp?page="+page+"&kind="+kind+"&keyword="+keyword+"&os="+os+"&limit="+limit);
			forward.setPath("template_sub.jsp");
		}
		return forward;
	}

}
