package member.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import member.dao.admin.MemberDAO;
import vo.admin.Member;

public class MemberModifyProService {
	public boolean updateMemberInfo(String index, Member member) {
		// TODO Auto-generated method stub
		boolean isModifySuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int updateCount = memberDAO.updateMemberInfo(index, member);
		if (updateCount > 0) { //수정이 완료한 경우
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		if (con != null) close(con);
		return isModifySuccess;
	}

}
