package lib.action.lib;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.svc.lib.LibAdminModifyProService;
import member.svc.admin.MemberModifyProService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Admin;
import vo.admin.Member;

public class LibAdminModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String index = request.getParameter("index");
		LibAdminModifyProService libAdminModifyProService = new LibAdminModifyProService();
		Admin admin = new Admin();
		admin.setAdminNum(request.getParameter("index") != null ? request.getParameter("index") : "");
		admin.setAdminId(request.getParameter("id") != null ? request.getParameter("id") : "");
		admin.setAdminPassword(request.getParameter("pass") != null ? request.getParameter("pass") : "");
		admin.setAdminName(request.getParameter("name") != null ? request.getParameter("name") : "");
		admin.setAdminEmail(request.getParameter("email") != null ? request.getParameter("email") : "");
		admin.setAdminTel(request.getParameter("tel") != null ? request.getParameter("tel") : "");
		boolean isModifySuccess = libAdminModifyProService.updateLibAdminInfo(index, admin);
		
		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정이 완료되지 않았습니다.');");
			out.println("history.back();");
			out.println("</script>");
		} else {
//			request.setAttribute("pagefile", "/member/memberInfo.mem");
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("LibAdminInfo.libL");
		}
		return forward;
	}

}
