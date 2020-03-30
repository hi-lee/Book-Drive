package member.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.admin.Action;
import vo.admin.ActionForward;
import member.svc.admin.AdminListService;
import vo.admin.Admin;

public class AdminListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		AdminListService adminListService = new AdminListService();
		adminList = adminListService.selectAdminList();
		request.setAttribute("pagefile", "/member/admin_adminList.jsp");
		request.setAttribute("adminList", adminList);
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}

}
