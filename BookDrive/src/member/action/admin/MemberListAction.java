package member.action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.admin.Action;
import vo.admin.ActionForward;
import member.svc.admin.MemberListService;
import vo.admin.Member;

public class MemberListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		ArrayList<Member> memberList = new ArrayList<Member>();
		MemberListService memberListService = new MemberListService();
		memberList = memberListService.selectMemberList();
		request.setAttribute("pagefile", "/member/admin_memberList.jsp");
		request.setAttribute("memberList", memberList);
		forward = new ActionForward();
		forward.setPath("admin_template.jsp");
		return forward;
	}

}
