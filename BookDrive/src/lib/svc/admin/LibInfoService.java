package lib.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import lib.dao.admin.LibDAO;
import vo.admin.Admin;
import vo.admin.Library;

public class LibInfoService {
	public Library selectLibraryInfo(String code) {
		//도서관 상세보기
		Library library = null;
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		library = libDAO.getLibraryInfo(code);
		if (con != null) close(con);
		return library;
	}

	public ArrayList<Admin> selectLibraryAdminInfo(String code) {
		//도서관 소속 관리자정보
		ArrayList<Admin> libAdminList = null;
		Connection con = getConnection();
		LibDAO libDAO = LibDAO.getInstance();
		libDAO.setConnection(con);
		libAdminList = libDAO.getLibAdminList(code);
		if (con != null) close(con);
		return libAdminList;
	}

}
