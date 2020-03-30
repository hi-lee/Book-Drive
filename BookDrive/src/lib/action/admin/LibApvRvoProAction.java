package lib.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import lib.svc.admin.LibApvRvoProService;
import vo.admin.ActionVoid;

public class LibApvRvoProAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String adminNum = request.getParameter("adminNum");
		String adminId = request.getParameter("adminId");
		LibApvRvoProService libApvRvoProService = new LibApvRvoProService();
		boolean result = libApvRvoProService.updateLibApvRvo(adminNum, adminId);
		System.out.println(result);
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(obj);
	}
}