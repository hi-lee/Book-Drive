package log.svc.lib;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import log.dao.lib.LogDAO;
import vo.admin.Admin;

public class LoginService {
	public Admin adminLogin(String id) {
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		Admin admin = logDAO.getAdminInfo(id);
		if (con != null) close(con);
		return admin;
	}
}
