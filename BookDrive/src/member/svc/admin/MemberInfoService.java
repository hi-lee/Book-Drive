package member.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

import member.dao.admin.MemberDAO;
import vo.admin.Member;

public class MemberInfoService {
	public Member selectMemberInfo(String id, String index) {
		// TODO Auto-generated method stub
		Member member = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		member = memberDAO.getMemberInfo(id, index);
		if (con != null) close(con);
		return member;
	}
}
