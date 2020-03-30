package lib.action.lib;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lib.svc.lib.LibModifyProService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Library;

public class LibModifyProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		boolean isUpdateCheck = false;
		Library library = new Library();
		library.setLibCode(request.getParameter("code") != null ? request.getParameter("code") : "");
		library.setLibName(request.getParameter("name") != null ? request.getParameter("name") : "");
		library.setLibTel(request.getParameter("tel") != null ? request.getParameter("tel") : "");
		library.setLibFax(request.getParameter("fax") != null ? request.getParameter("fax") : "");
		library.setLibHomePage(request.getParameter("homepage") != null ? request.getParameter("homepage") : "");
		library.setLibZip(request.getParameter("zip") != null ? request.getParameter("zip") : "");
		library.setLibAddr1(request.getParameter("addr1") != null ? request.getParameter("addr1") : "");
		library.setLibAddr2(request.getParameter("addr2") != null ? request.getParameter("addr2") : "");
		library.setLibLo(request.getParameter("lo") != null ? request.getParameter("lo") : "");
		library.setLibLa(request.getParameter("la") != null ? request.getParameter("la") : "");
		LibModifyProService libModifyProService = new LibModifyProService();
		isUpdateCheck = libModifyProService.updateLibrary(library);
		
		if (!isUpdateCheck) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정에 실패하였습니다');");
			out.println("history.back();");
			out.println("</script>");
		} else {
			forward = new ActionForward();
			request.setAttribute("library", library);
			forward.setPath("LibraryInfo.libL?libcode=" + library.getLibCode());
		}
		return forward;
	}

}
