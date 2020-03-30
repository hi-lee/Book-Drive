package lib.dao.lib;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.admin.Admin;
import vo.admin.LibAdminApv;
import vo.admin.Library;
import vo.admin.Member;

public class LibDAO {
	DataSource ds;
	Connection con;
	private static LibDAO libDAO;
	
	private LibDAO() {}
	
	public static LibDAO getInstance() {
		if (libDAO == null) {
			libDAO = new LibDAO();
		}
		return libDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<Library> getLibraryList(String code) {
		//도서관 리스트(code값에 따라 출력, code가 null이면 전체 도서관 리스트 출력)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Library library = null;
		ArrayList<Library> libraryList = new ArrayList<Library>();
		String query = "select * from library";
//		if (code != null || code != "" || code != "null") {
//			query = query + " where libCode = " + code;
//		}
		try {
			System.out.println(code + ", " + query);
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				library = new Library();
				library.setLibCode(rs.getString("libCode"));
				library.setLibName(rs.getString("libName"));
				library.setLibZip(rs.getString("libZip"));
				library.setLibAddr1(rs.getString("libAddr1"));
				library.setLibAddr2(rs.getString("libAddr2"));
				library.setLibTel(rs.getString("libTel"));
				library.setLibFax(rs.getString("libFax"));
				library.setLibHomePage(rs.getString("libHomePage"));
				library.setLibLa(rs.getString("libLa"));
				library.setLibLo(rs.getString("libLo"));
				libraryList.add(library);
			}
		} catch (Exception e) {
			System.out.println("getLibraryList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return libraryList;
	}
	
	public Library getLibraryInfo(String code) {
		//도서관 상세보기
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Library library = null;
		String query = "select * from library where libCode = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				library = new Library();
				library.setLibCode(rs.getString("libCode"));
				library.setLibName(rs.getString("libName"));
				library.setLibZip(rs.getString("libZip"));
				library.setLibAddr1(rs.getString("libAddr1"));
				library.setLibAddr2(rs.getString("libAddr2"));
				library.setLibTel(rs.getString("libTel"));
				library.setLibFax(rs.getString("libFax"));
				library.setLibHomePage(rs.getString("libHomePage"));
				library.setLibLa(rs.getString("libLa"));
				library.setLibLo(rs.getString("libLo"));
			}
		} catch (Exception e) {
			System.out.println("getLibraryInfo 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return library;
	}
	
	public ArrayList<Admin> getLibAdminList(String code) {
		//도서관관리자 리스트(도서관 상세보기 페이지 연동)
		System.out.println("code");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Admin admin = null;
		ArrayList<Admin> libAdminList = new ArrayList<Admin>();
		String query = "select * from admin where libCode = ? and adminGrade = 'L' and adminApv = 'YES'";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				admin = new Admin();
				admin.setAdminNum(rs.getString("adminNum"));
				admin.setLibCode(rs.getString("libCode"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminTel(rs.getString("adminTel"));
				admin.setAdminApv(rs.getString("adminApv"));
				admin.setAdminGrade(rs.getString("adminGrade"));
				admin.setAdminId(rs.getString("adminId"));
				admin.setAdminPassword(rs.getString("adminPass"));
				admin.setAdminEmail(rs.getString("adminEmail"));
				libAdminList.add(admin);
			}
		} catch (Exception e) {
			System.out.println("getLibAdminInfo 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return libAdminList;
	}
	
	public ArrayList<LibAdminApv> getLibraryApvList() {
		//도서관 관리자 가입신청 리스트()
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LibAdminApv libAdminApv = null;
		ArrayList<LibAdminApv> libraryApvList = new ArrayList<LibAdminApv>();
		String query = "SELECT l.libCode, l.libName, l.libTel, a.adminNum, a.adminName, a.adminId, a.adminTel" + 
						" FROM library AS l" + 
						" JOIN admin AS a" + 
						" ON l.libCode = a.libCode" + 
						" WHERE a.adminApv = 'NO'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				libAdminApv = new LibAdminApv();
				libAdminApv.setLibCode(rs.getString("libCode"));
				libAdminApv.setLibName(rs.getString("libName"));
				libAdminApv.setLibTel(rs.getString("libTel"));
				libAdminApv.setAdminNum(rs.getString("adminNum"));
				libAdminApv.setAdminName(rs.getString("adminName"));
				libAdminApv.setAdminId(rs.getString("adminId"));
				libAdminApv.setAdminTel(rs.getString("adminTel"));
				libraryApvList.add(libAdminApv);
			}
		} catch (Exception e) {
			System.out.println("getLibraryApvList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return libraryApvList;
	}

	public int setLibraryApvPro(String adminNum, String adminId) {
		//도서관 관리자 가입신청 승인
		System.out.println("adminNum : " + adminNum + ", adminId : " + adminId);
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update admin set adminApv = 'YES' where adminNum = " + adminNum + " and adminId = '" + adminId + "'";
		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setLibraryApvUpdate 에러 : " + ex);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		
		return updateCount;
	}
	
	public int setLibraryApvRvoPro(String adminNum, String adminId) {
		//도서관 관리자 가입신청 승인 취소
		System.out.println("adminNum : " + adminNum + ", adminId : " + adminId);
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update admin set adminApv = 'NO' where adminNum = " + adminNum + " and adminId = '" + adminId + "'";
		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setLibraryApvRvoUpdate 에러 : " + ex);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		
		return updateCount;
	}
	
	public ArrayList<Admin> getLibAdminList() {
		//도서관관리자 리스트
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Admin admin = null;
		ArrayList<Admin> libAdminList = new ArrayList<Admin>();
		String query = "SELECT a.*, l.libName" + 
						" FROM library AS l" + 
						" JOIN admin AS a" + 
						" ON l.libCode = a.libCode" + 
						" WHERE a.libCode = ? AND a.adminApv = 'YES'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				admin = new Admin();
				admin.setAdminNum(rs.getString("adminNum"));
				admin.setLibCode(rs.getString("libCode"));
				admin.setLibName(rs.getString("libName"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminTel(rs.getString("adminTel"));
				admin.setAdminApv(rs.getString("adminApv"));
				admin.setAdminGrade(rs.getString("adminGrade"));
				admin.setAdminId(rs.getString("adminId"));
				admin.setAdminPassword(rs.getString("adminPass"));
				admin.setAdminEmail(rs.getString("adminEmail"));
				libAdminList.add(admin);
			}
		} catch (Exception e) {
			System.out.println("getLibAdminList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return libAdminList;
	}

	public Admin getLibAdminInfo(String id, String index) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Admin admin = null;
		String query = "SELECT * FROM admin where adminNum = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, index);
			rs = pstmt.executeQuery();
			if (rs.next()) { //회원정보는 member에 저장하고
				admin = new Admin();
				admin.setAdminNum(rs.getString("adminNum"));
				admin.setLibCode(rs.getString("libCode"));
				admin.setAdminId(rs.getString("adminId"));
				admin.setAdminPassword(rs.getString("adminPass"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminTel(rs.getString("adminTel"));
				admin.setAdminEmail(rs.getString("adminEmail"));
				admin.setAdminGrade(rs.getString("adminGrade"));
				admin.setAdminApv(rs.getString("adminApv"));
			}
		} catch (Exception e) {
			System.out.println("getLibAdminInfo 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return admin;
	}

	public int updateLibAdminInfo(String index, Admin admin) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update admin set adminId = ?, adminPass = ?, adminName = ?, adminEmail = ?, adminTel = ? where adminNum = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getAdminId());
			pstmt.setString(2, admin.getAdminPassword());
			pstmt.setString(3, admin.getAdminName());
			pstmt.setString(4, admin.getAdminEmail());
			pstmt.setString(5, admin.getAdminTel());
			pstmt.setString(6, index);
			updateCount = pstmt.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("updateLibAdminInfo 에러 : " + e);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		
		return updateCount;
	}

	public int updateLibrary(Library library) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update library set "
				+ "libName = ?, "
				+ "libZip = ?, "
				+ "libAddr1 = ?, "
				+ "libAddr2 = ?, "
				+ "libTel = ?, "
				+ "libFax = ?, "
				+ "libHomePage = ?, "
				+ "libLa = ?, "
				+ "libLo = ? "
				+ "where libCode = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, library.getLibName());
			pstmt.setString(2, library.getLibZip());
			pstmt.setString(3, library.getLibAddr1());
			pstmt.setString(4, library.getLibAddr2());
			pstmt.setString(5, library.getLibTel());
			pstmt.setString(6, library.getLibFax());
			pstmt.setString(7, library.getLibHomePage());
			pstmt.setString(8, library.getLibLa());
			pstmt.setString(9, library.getLibLo());
			pstmt.setString(10, library.getLibCode());
			updateCount = pstmt.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("updateLibrary 에러 :::: " + e);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		
		return updateCount;
	}
}
