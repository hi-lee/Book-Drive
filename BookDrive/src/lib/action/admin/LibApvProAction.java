package lib.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import lib.svc.admin.LibApvProService;
import vo.admin.ActionVoid;

public class LibApvProAction implements ActionVoid {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String adminNum = request.getParameter("adminNum");
		String adminId = request.getParameter("adminId");
		LibApvProService libApvProService = new LibApvProService();
		boolean result = libApvProService.updateLibApv(adminNum, adminId);
		System.out.println(result);
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(obj);
	}
}