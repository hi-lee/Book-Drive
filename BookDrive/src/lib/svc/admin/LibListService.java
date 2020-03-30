package lib.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import lib.dao.admin.LibDAO;
import vo.admin.Library;

public class LibListService {
	public ArrayList<Library> selectLibList(String code) {
		// TODO Auto-generated method stub
		ArrayList<Library> libraryList = null;
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		libraryList = libDAO.getLibraryList(code);
		if (con != null) close(con);
		return libraryList;
	}

}
