package member.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;
import member.vo.Member;

public class MemberJoinProSvc {

	public boolean memberJoin(Member member) {
		// TODO Auto-generated method stub
		boolean joinResult = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int insertMember = memberDAO.insertMember(member);
		
		if(insertMember > 0) {
			commit(con);
			joinResult = true;
		} else {
			rollback(con);
		}
		close(con);
		return joinResult;
	}

}
