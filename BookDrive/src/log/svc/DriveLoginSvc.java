package log.svc;

import java.sql.Connection;

import dao.MemberDAO;
import member.vo.Member;
import static db.JdbcUtil.*;

public class DriveLoginSvc {

	public Member driveLogin(String memIndex) {
		// TODO Auto-generated method stub
		Member member = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		member = memberDAO.selectDriveMember(memIndex);
		close(con);
		return member;
	}

}
