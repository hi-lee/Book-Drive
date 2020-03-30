package lib.action.lib;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.svc.admin.LibInfoService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Admin;
import vo.admin.Library;

public class LibInfoAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String code = request.getParameter("libcode") != null ? request.getParameter("libcode") : "";
		Library library = null;
		ArrayList<Admin> libAdminList = null;
		LibInfoService libInfoService = new LibInfoService();
		library = libInfoService.selectLibraryInfo(code);
//		libAdminList = libInfoService.selectLibraryAdminInfo(code);
		request.setAttribute("pagefile", "/library/lib_LibInfo.jsp");
		request.setAttribute("library", library);
//		request.setAttribute("libAdminList", libAdminList);
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}

}
