package log.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import log.svc.DriveLoginSvc;
import member.vo.Member;

public class DriveLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String memIndex = request.getParameter("memIndex");
		ActionForward forward = null;
		Member member = null;

		DriveLoginSvc driveLoginSvc = new DriveLoginSvc();
		member = driveLoginSvc.driveLogin(memIndex);

		if (member == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원번호/차량번호가 존재하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			HttpSession session = request.getSession();
			memIndex = member.getIndex();
			session.setAttribute("memIndex", memIndex);
			session.setAttribute("member", member);
			forward = new ActionForward();
			forward.setPath("/bookSearchPro.bk?usage=1");

		}
		return forward;
	}

}
