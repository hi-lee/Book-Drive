package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import log.svc.LoginSvc;
import member.vo.Member;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String chk_id = request.getParameter("id");
		
		if(chk_id == null || chk_id.equals("")) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location.href='idCheck.mem'");
			out.println("</script>");
		} else {
			LoginSvc idCheckSvc = new LoginSvc();
			Member member = idCheckSvc.memberLogin(chk_id);
			
			if(member == null) {
				request.setAttribute("passibleId", true);				
			} else {
				request.setAttribute("passibleId", false);
			}
			request.setAttribute("id", chk_id);
			forward = new ActionForward();
			forward.setPath("member/idCheck.jsp");
		}
		return forward;
	}

}
