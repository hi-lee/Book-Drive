package member.svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import member.dao.admin.MemberDAO;

public class MemberDeleteProService {
	public boolean deleteMember(String memIndex) {
		// TODO Auto-generated method stub
		boolean isDeleteCheck = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int deleteCount = memberDAO.deleteMember(memIndex);
		
		if (deleteCount > 0) {
			isDeleteCheck = true;
			commit(con);
		} else {
			rollback(con);
		}
		if (con != null) close(con);
		return isDeleteCheck;
	}

}
