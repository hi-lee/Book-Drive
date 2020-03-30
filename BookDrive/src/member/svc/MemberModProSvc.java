package member.svc;

import java.sql.Connection;

import dao.MemberDAO;
import member.vo.Member;
import static db.JdbcUtil.*;

public class MemberModProSvc {

	public boolean memberMod(Member member) {
		// TODO Auto-generated method stub
		boolean isModifySuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int updateCount = memberDAO.updateMember(member);
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		return isModifySuccess;
	}

}
