package log.action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vo.admin.Action;
import vo.admin.ActionForward;

public class LogoutAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("adminCheck");
		if (session.getAttribute("id") == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그아웃 되었습니다');");
			out.println("location.href='AdminloginForm.logC';");
			out.println("</script>");
//			forward = new ActionForward();
//			forward.setRedirect(true);
//			forward.setPath("AdminloginForm.logC");
		}
		return forward;
	}

}
