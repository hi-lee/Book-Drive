package log.action.lib;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import log.svc.lib.LoginService;
import vo.admin.Action;
import vo.admin.Admin;
import vo.admin.ActionForward;

public class LoginAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		System.out.println("도서관관리자 로그인 액션 아이디 : " + id + ", 비밀번호 : " + pass);
		LoginService loginService = new LoginService();
		Admin admin = loginService.adminLogin(id);
		
		if (admin == null) { //아이디 불일치
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			if (!pass.equals(admin.getAdminPassword())) { //아이디 일치, 비밀번호 불일치
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('비밀번호가 일치하지 않습니다.')");
				out.println("history.back();");
				out.println("</script>");
			} else { //아이디, 비밀번호 일치
				HttpSession session = request.getSession();
				session.setAttribute("id", id); //아이디 세션값
				session.setAttribute("index", admin.getAdminNum()); //관리자번호 세션값 
				session.setAttribute("code", admin.getLibCode()); //도서관 코드 세션값
				session.setAttribute("libname", admin.getLibName()); //도서관 이름 세션값
				session.setAttribute("libCheck", true); //통합관리자 로그인 세션값
				session.setMaxInactiveInterval(3600);
				forward = new ActionForward();
				forward.setPath("Adminmain.logL");
			}
		}
		return forward;
	}
}