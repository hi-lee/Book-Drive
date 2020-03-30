package lib.svc.lib;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import lib.dao.lib.LibDAO;
import member.dao.admin.MemberDAO;
import vo.admin.Admin;

public class LibAdminModifyProService {
	public boolean updateLibAdminInfo(String index, Admin admin) {
		// TODO Auto-generated method stub
		boolean isModifySuccess = false;
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		int updateCount = libDAO.updateLibAdminInfo(index, admin);
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
