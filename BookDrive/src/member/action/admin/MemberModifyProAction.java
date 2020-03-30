package member.action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.svc.admin.MemberModifyProService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Member;

public class MemberModifyProAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String index = request.getParameter("index");
		MemberModifyProService memberModifyProService = new MemberModifyProService();
		Member member = new Member();
		member.setMemId(request.getParameter("id")!=null?request.getParameter("id"):"");
		member.setMemPass(request.getParameter("pass")!=null?request.getParameter("pass"):"");
		member.setMemName(request.getParameter("id")!=null?request.getParameter("id"):"");
		member.setMemBirth(request.getParameter("birthday") != null ? request.getParameter("birthday") : "");
		member.setMemTel(request.getParameter("tel") != null ? request.getParameter("tel") : "");
		member.setMemZip(request.getParameter("zip") != null ? request.getParameter("zip") : "");
		member.setMemAddr1(request.getParameter("addr1") != null ? request.getParameter("addr1") : "");
		member.setMemAddr2(request.getParameter("addr2") != null ? request.getParameter("addr2") : "");
		member.setMemEmail(request.getParameter("email") != null ? request.getParameter("email") : "");
		member.setMemCarNum(request.getParameter("carnum") != null ? request.getParameter("carnum") : "");
		boolean isModifySuccess = memberModifyProService.updateMemberInfo(index, member);
		
		if (!isModifySuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('수정이 완료되지 않았습니다.');");
			out.println("history.back();");
			out.println("</script>");
		} else {
//			request.setAttribute("pagefile", "/member/memberInfo.mem");
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("MemberInfo.memA?id=" + request.getParameter("id") + "&index=" + index); //수정
		}
		return forward;
	}

}
