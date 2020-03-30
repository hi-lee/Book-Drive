package lib.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.svc.admin.LibApvListService;
import lib.svc.admin.LibListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.LibAdminApv;
import vo.admin.Library;

public class LibApvListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<LibAdminApv> libraryApvList = new ArrayList<LibAdminApv>();
		LibApvListService libApvListService = new LibApvListService();
		libraryApvList = libApvListService.selectLibApvList();
		
		request.setAttribute("pagefile", "/library/admin_LibApvList.jsp");
		request.setAttribute("libraryApvList", libraryApvList);
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}

}
