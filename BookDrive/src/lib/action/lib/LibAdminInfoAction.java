package lib.action.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lib.svc.lib.LibAdminInfoService;
import member.svc.admin.MemberInfoService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Admin;
import vo.admin.Member;

public class LibAdminInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		String id = session.getAttribute("id") != null ? (String) session.getAttribute("id") : "";
		String index = session.getAttribute("index") != null ? (String) session.getAttribute("index") : "";
		Admin admin = new Admin();
		LibAdminInfoService libAdminInfoService = new LibAdminInfoService();
		if (!index.equals("")) {
			admin = libAdminInfoService.selectLibAdminInfo(id, index);
		}
		request.setAttribute("pagefile", "/member/lib_memberInfo.jsp");
		request.setAttribute("admin", admin);
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}

}
