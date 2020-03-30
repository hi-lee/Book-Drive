package lib.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import lib.dao.admin.LibDAO;

public class LibApvRvoProService {
	public boolean updateLibApvRvo(String adminNum, String adminId) { //도서관 관리자 가입을 승인하는 메소드
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		int updateCount = libDAO.setLibraryApvRvoPro(adminNum, adminId);
		boolean result = false;
		if (updateCount > 0) {
			commit(con);
			result = true;
		} else {
			rollback(con);
		}
		
		if (con != null) close(con);
		return result;
	}
}