package log.action.lib;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import log.svc.lib.MainService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;
import vo.admin.Book;
import vo.admin.MainCount;

public class MainAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String loginCode = (String) session.getAttribute("code");
		MainCount mainCount = null; //대여현황 카운트
		ArrayList<Book> bookList1 = new ArrayList<>(); //신착도서
		ArrayList<Book> bookList2 = new ArrayList<>(); //인기도서
		ArrayList<BoardBean> boardList1 = new ArrayList<>(); //공지사항
		ArrayList<BoardBean> boardList2 = new ArrayList<>(); //QnA
		ArrayList<BoardBean> boardList3 = new ArrayList<>(); //희망도서
		ArrayList<BoardBean> boardList4 = new ArrayList<>(); //자유게시판
		MainService mainService = new MainService();
		mainCount = mainService.selectRentalCount(loginCode);
		
		bookList1 = mainService.selectBook1(loginCode);
		bookList2 = mainService.selectBook2(loginCode);
		
		boardList1 = mainService.selectBoard1();
		boardList2 = mainService.selectBoard2(loginCode);
		boardList3 = mainService.selectBoard3(loginCode);
		boardList4 = mainService.selectBoard4();
		
		//boardList 로그 찍기
		forward = new ActionForward();
		request.setAttribute("maincount", mainCount);
		request.setAttribute("booklist1", bookList1);
		request.setAttribute("booklist2", bookList2);
		request.setAttribute("boardlist1", boardList1);
		request.setAttribute("boardlist2", boardList2);
		request.setAttribute("boardlist3", boardList3);
		request.setAttribute("boardlist4", boardList4);
		request.setAttribute("pagefile", "/member/lib_main.jsp");
		forward.setPath("lib_template.jsp");
		return forward;
	}

}
