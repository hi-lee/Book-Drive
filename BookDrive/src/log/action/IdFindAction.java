package log.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import log.svc.IdFindSvc;
import member.vo.Member;

public class IdFindAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String name = request.getParameter("userName");
		String birth = request.getParameter("userBirth");
		IdFindSvc idFindSvc = new IdFindSvc();
		Member memberId = idFindSvc.memberId(name, birth);
		
		if(memberId != null) { // memberId 객체가 있으면 setAttribute, 없으면 객체 생성X
			request.setAttribute("isTrue", true);
			request.setAttribute("memberId", memberId);
		} else {
			request.setAttribute("isTrue", false);
		}	
		forward = new ActionForward();
		forward.setPath("member/idFind.jsp");
		return forward;
	}

}
