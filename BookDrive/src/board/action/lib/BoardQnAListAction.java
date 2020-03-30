package board.action.lib;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.svc.lib.BoardListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;
import vo.admin.PageInfo;

public class BoardQnAListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();
		String kind = request.getParameter("kind") != null ? request.getParameter("kind") : "title"; //제목, 작성자, 내용
		String keyword = request.getParameter("keyword") != null ? request.getParameter("keyword") : ""; //검색값
		String os = request.getParameter("os") != null ? request.getParameter("os") : "desc"; //DESC, ASC
		String flag = "2"; //boardFlag
		String libCode = request.getParameter("libcode") != null ? request.getParameter("libcode") : ""; //도서관관리자용 코드
		
		int nowPage = 1; //디폴트(첫페이지)
		int limit = request.getParameter("CountPerPage") != null ? Integer.parseInt(request.getParameter("CountPerPage")) : 10; //페이지에 보여줄 목록 수
		int limitPage = 10; //페이지 수
		
		if (request.getParameter("page") != null) {
			nowPage = Integer.parseInt(request.getParameter("page"));
		}
		
		BoardListService boardListService = new BoardListService();
		int listCount = boardListService.getListCount(kind, keyword, flag, libCode); //총 리스트 수를 받아옴 (총 글 갯수)
		boardList = boardListService.selectBoardList(nowPage, limit, kind, keyword, flag, os, libCode);
		
		int maxPage = (int) ((double) listCount / limit + 0.95); //총 페이지 수, 0.95를 더해서 올림 처리
		int startPage = (((int) ((double) nowPage / limitPage + 0.9)) -1) * limitPage + 1; //현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등등)
		int endPage = startPage + limitPage - 1;
		if (endPage > maxPage) endPage = maxPage;
		PageInfo pageInfo = new PageInfo();
		pageInfo.setEndPage(endPage);
		pageInfo.setListCount(listCount);
		pageInfo.setMaxPage(maxPage);
		pageInfo.setnowPage(nowPage);
		pageInfo.setStartPage(startPage);
		
		request.setAttribute("pagefile", "/board/lib_boardQnAList.jsp?page="+nowPage+"&kind="+kind+"&keyword="+keyword+"&os="+os+"&limit="+limit);
		request.setAttribute("pageinfo", pageInfo);
		request.setAttribute("boardList", boardList);
		
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}
}