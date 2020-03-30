package log.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import log.dao.admin.LogDAO;
import vo.admin.Admin;

public class LoginService {
	public Admin adminLogin(String id) {
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		System.out.println("로그인서비스 아이디 : " + id);
		Admin admin = logDAO.getAdminInfo(id);
		if (con != null) close(con);
		return admin;
	}
}
