package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import action.ActionForward;
import member.svc.MemberModProSvc;
import member.vo.Member;

public class MemModProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("userID") == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인을 하세요!!')");
			out.println("location.href='loginForm.log'");
			out.println("</script>");
		} else {
			request.setCharacterEncoding("UTF-8");
			String id = (String)session.getAttribute("userID");
			Member member = new Member();
			member.setId(request.getParameter("userID"));
			member.setPassword(request.getParameter("userPass"));
			member.setName(request.getParameter("userName"));
			member.setTel(request.getParameter("userPhone"));
			member.setBirth(request.getParameter("userBirth"));
			member.setEmail(request.getParameter("userEmailId") + "@" + request.getParameter("userEmailAd"));
			member.setZip(request.getParameter("userZip"));
			member.setAddr1(request.getParameter("userAddr1"));
			member.setAddr2(request.getParameter("userAddr2"));
			member.setCarNum(request.getParameter("userCarNum"));
			
			MemberModProSvc memberModProSvc = new MemberModProSvc();
			boolean isModifySuccess = memberModProSvc.memberMod(member);
			
			if(!isModifySuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('수정실패');");
				out.print("history.back();");
				out.print("</script>");
			} else {
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("memInfo.mem?id=" + member.getId());
			}
		}
		return forward;
	}

}
