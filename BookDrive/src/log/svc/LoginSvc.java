package log.svc;

import java.sql.Connection;

import dao.MemberDAO;
import member.vo.Member;
import static db.JdbcUtil.*;

public class LoginSvc {

	public Member memberLogin(String id) {
		// TODO Auto-generated method stub
		Member member = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		member = memberDAO.selectMember(id);
		close(con);						
		return member;
	}

}
