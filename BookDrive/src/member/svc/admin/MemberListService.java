package member.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

import member.dao.admin.MemberDAO;
import vo.admin.Member;

public class MemberListService {
	public ArrayList<Member> selectMemberList() {
		// TODO Auto-generated method stub
		ArrayList<Member> memberList = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		memberList = memberDAO.getMemberList();
		if (con != null) close(con);
		return memberList;
	}

}
