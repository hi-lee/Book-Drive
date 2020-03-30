package lib.svc.lib;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import lib.dao.lib.LibDAO;
import vo.admin.Library;

public class LibModifyProService {
	public boolean updateLibrary(Library library) {
		// TODO Auto-generated method stub
		boolean isUpdateSuccess = false;
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		int updateCount = libDAO.updateLibrary(library);
		if (updateCount > 0) { //수정이 완료한 경우
			commit(con);
			isUpdateSuccess = true;
		} else {
			rollback(con);
		}
		if (con != null) close(con);
		return isUpdateSuccess;
	}

}
