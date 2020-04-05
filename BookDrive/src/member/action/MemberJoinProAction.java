package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import member.svc.MemberJoinProSvc;
import member.vo.Member;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Member member = new Member();
		ActionForward forward = null;
		boolean joinResult = false;
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
		
		if (request.getParameter("userCheck").equals("member")) { //일반사용자 가입
			MemberJoinProSvc memberJoinProSvc = new MemberJoinProSvc();
			joinResult = memberJoinProSvc.memberJoin(member);
		} else { //도서관관리자 가입
			String libcode = request.getParameter("libcode");
			MemberJoinProSvc memberJoinProSvc = new MemberJoinProSvc();
			joinResult = memberJoinProSvc.adminJoin(member, libcode);
		}
		
		System.out.println(request.getParameter("userID") + joinResult);
		
		if (!joinResult) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.print("alret('회원 가입에 오류가 발생했습니다. 다시 작성하세요.')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.print("alret('회원가입에 성공했습니다.');");
			out.println("</script>");
			forward = new ActionForward();
			forward.setPath("loginForm.log");
		}
		return forward;
	}
}
