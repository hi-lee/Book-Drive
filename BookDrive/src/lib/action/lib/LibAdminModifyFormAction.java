package lib.action.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lib.svc.lib.LibAdminInfoService;
import lib.svc.lib.LibAdminModifyService;
import member.svc.admin.MemberInfoService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Admin;
import vo.admin.Member;

public class LibAdminModifyFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String id = request.getParameter("id") != null ? request.getParameter("id") : "";
		String index = request.getParameter("index") != null ? request.getParameter("index") : "";
		Admin admin = new Admin();
		LibAdminInfoService libAdminInfoService = new LibAdminInfoService();
		admin = libAdminInfoService.selectLibAdminInfo(id, index);
		request.setAttribute("pagefile", "/member/lib_memberModifyForm.jsp");
		request.setAttribute("admin", admin);
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}

}
