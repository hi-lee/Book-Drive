package lib.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import lib.dao.admin.LibDAO;
import vo.admin.LibAdminApv;

public class LibApvListService {
	public ArrayList<LibAdminApv> selectLibApvList() {
		// TODO Auto-generated method stub
		ArrayList<LibAdminApv> libraryApvList = null;
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		libraryApvList = libDAO.getLibraryApvList();
		if (con != null) close(con);
		return libraryApvList;
	}

}
