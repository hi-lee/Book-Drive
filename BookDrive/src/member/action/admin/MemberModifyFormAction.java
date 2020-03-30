package member.action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.svc.admin.MemberInfoService;
import vo.admin.Action;
import vo.admin.ActionForward;
import vo.admin.Member;

public class MemberModifyFormAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		String id = request.getParameter("id") != null ? request.getParameter("id") : "";
		String index = request.getParameter("index") != null ? request.getParameter("index") : "";
		Member member = new Member();
		MemberInfoService memberInfoService = new MemberInfoService();
		member = memberInfoService.selectMemberInfo(id, index);
		request.setAttribute("pagefile", "/member/admin_memberModifyForm.jsp");
		request.setAttribute("member", member);
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}

}
