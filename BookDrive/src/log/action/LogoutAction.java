package log.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;

public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.invalidate();
		ActionForward forward = new ActionForward();
		request.setAttribute("pageIn", "member/logout.jsp");
		forward.setPath("template_sub.jsp");
		return forward;
	}

}
