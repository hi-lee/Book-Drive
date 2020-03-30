package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import log.svc.LoginSvc;
import member.vo.Member;

public class MemModAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String id = null;
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userID") == null){ // if session: userID is null then location to loginForm
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 하세요!')");
			out.println("location.href='loginForm.log'");
			out.println("</script>");
		} else {
			id = (String)session.getAttribute("userID");
		}
		if(request.getParameter("id") != null && id !=null) { //if parameter:id is not null and id is not null
			id = request.getParameter("id");
		}
		if(id != null && !id.equals("")) { 
			LoginSvc memberModForm = new LoginSvc();
			Member member = memberModForm.memberLogin(id);
			request.setAttribute("member", member);
			request.setAttribute("pageIn", "/member/memMod.jsp");
			forward = new ActionForward();
			forward.setPath("template_sub.jsp");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 하세요!')");
			out.println("location.href='loginForm.log'");
			out.println("</script>");		
		}
		return forward;
	}

}
