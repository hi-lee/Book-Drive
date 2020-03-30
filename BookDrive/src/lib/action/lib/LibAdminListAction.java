package lib.action.lib;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.svc.lib.LibAdminListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Admin;

public class LibAdminListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<Admin> libAdminList = new ArrayList<Admin>();
		LibAdminListService libAdminListService = new LibAdminListService();
		libAdminList = libAdminListService.selectLibAdminList();
		request.setAttribute("pagefile", "/library/admin_LibAdminList.jsp");
		request.setAttribute("libAdminList", libAdminList);
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}
}