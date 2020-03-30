package lib.action.lib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.svc.admin.LibInfoService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Library;

public class LibModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String libcode = request.getParameter("libcode") != null ? request.getParameter("libcode") : "";
		Library library = null;
		LibInfoService libInfoService = new LibInfoService();
		library = libInfoService.selectLibraryInfo(libcode);
		request.setAttribute("pagefile", "/library/lib_LibModifyForm.jsp");
		request.setAttribute("library", library);
		forward = new ActionForward();
		forward.setPath("lib_template.jsp");
		return forward;
	}

}
