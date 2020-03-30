package log.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.MemberDAO;
import member.vo.Member;

public class IdFindSvc {

	public Member memberId(String name, String birth) {
		// TODO Auto-generated method stub
		Member memberId = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		memberId = memberDAO.getMemberId(name, birth);
		close(con);
		return memberId;
	}

}
