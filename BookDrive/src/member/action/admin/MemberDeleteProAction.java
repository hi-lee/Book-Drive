package member.action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.svc.admin.MemberDeleteProService;
import vo.admin.Action;
import vo.admin.ActionForward;

public class MemberDeleteProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String memIndex = request.getParameter("memindex");
		MemberDeleteProService memberDeleteProService = new MemberDeleteProService();
		boolean isDeleteCheck = memberDeleteProService.deleteMember(memIndex);
		
		if (!isDeleteCheck) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원 삭제에 실패하였습니다');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			forward.setPath("MemberList.memA");
		}
		return forward;
	}

}
