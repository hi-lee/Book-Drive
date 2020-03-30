package log.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import log.svc.LoginSvc;
import member.vo.Member;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("userID");
		String pass = request.getParameter("userPass");
		ActionForward forward = null;
		Member member = null;
		LoginSvc loginSvc = new LoginSvc();
		member = loginSvc.memberLogin(id);
		
		if(member == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('아이디가 존재하지 않습니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else {
			if(!pass.equals(member.getPassword())) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('아이디 비밀번호가 일치하지 않습니다');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				String index = member.getIndex();
				HttpSession session = request.getSession();
				session.setAttribute("userID", id);
				session.setAttribute("userIndex", index);
				forward = new ActionForward();
				forward.setPath("template_main.jsp");
			}
		}
		return forward;
	}
}
