package log.action.admin;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import log.svc.admin.MainService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.BoardBean;
import vo.admin.MainCount;

public class MainAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		MainCount mainCount = null;
		ArrayList<BoardBean> boardList1 = new ArrayList<>(); //공지사항
		ArrayList<BoardBean> boardList2 = new ArrayList<>(); //QnA
		ArrayList<BoardBean> boardList3 = new ArrayList<>(); //희망도서
		ArrayList<BoardBean> boardList4 = new ArrayList<>(); //자유게시판
		MainService mainService = new MainService();
		mainCount = mainService.selectRentalCount();
		boardList1 = mainService.selectBoard1();
		boardList2 = mainService.selectBoard2();
		boardList3 = mainService.selectBoard3();
		boardList4 = mainService.selectBoard4();
		
		//boardList 로그 찍기
		forward = new ActionForward();
		request.setAttribute("maincount", mainCount);
		request.setAttribute("boardlist1", boardList1);
		request.setAttribute("boardlist2", boardList2);
		request.setAttribute("boardlist3", boardList3);
		request.setAttribute("boardlist4", boardList4);
		request.setAttribute("pagefile", "/member/admin_main.jsp");
		forward.setPath("admin_template.jsp");
		return forward;
	}

}
