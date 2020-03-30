package lib.svc.lib;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import lib.dao.lib.LibDAO;
import vo.admin.Admin;

public class LibAdminListService {
	public ArrayList<Admin> selectLibAdminList() {
		// TODO Auto-generated method stub
		ArrayList<Admin> libAdminList = null;
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		libAdminList = libDAO.getLibAdminList();
		if (con != null) close(con);
		return libAdminList;
	}

}
