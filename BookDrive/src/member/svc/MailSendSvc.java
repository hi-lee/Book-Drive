package member.svc;

import java.sql.Connection;

import dao.MemberDAO;
import member.vo.Member;
import static db.JdbcUtil.*;
public class MailSendSvc {

	public int getMemInfo(String id, String pw, String receiver) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int updateCount = memberDAO.getMemInfo(id, pw, receiver);
		if(con != null) close(con);
				
		return updateCount;
	}

}
