package lib.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.svc.admin.LibListService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Library;

public class LibListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String code = request.getParameter("libcode") != null ? request.getParameter("libcode") : null;
		ArrayList<Library> libraryList = new ArrayList<Library>();
		LibListService libListService = new LibListService();
		libraryList = libListService.selectLibList(code);
		
		request.setAttribute("pagefile", "/library/admin_LibList.jsp");
		request.setAttribute("libraryList", libraryList);
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}

}
