package lib.svc.lib;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import lib.dao.lib.LibDAO;
import vo.admin.Admin;
import vo.admin.Member;

public class LibAdminInfoService {
	public Admin selectLibAdminInfo(String id, String index) {
		// TODO Auto-generated method stub
		Admin admin = null;
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		admin = libDAO.getLibAdminInfo(id, index);
		if (con != null) close(con);
		return admin;
	}

}
