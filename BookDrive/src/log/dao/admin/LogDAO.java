package log.dao.admin;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.sql.DataSource;

import vo.admin.Admin;
import vo.admin.BoardBean;
import vo.admin.MainCount;

public class LogDAO {
	DataSource ds;
	Connection con;
	private static LogDAO logDAO;
	
	private LogDAO() {}
	
	public static LogDAO getInstance() {
		if (logDAO == null) {
			logDAO = new LogDAO();
		}
		return logDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public Admin getAdminInfo(String id) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Admin admin = null;
		
		try {
			pstmt = con.prepareStatement("select * from admin where adminId = ? and adminGrade = 'C'");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("logDAO 조회성공 : " + rs.getString("adminId"));
				admin = new Admin();
				admin.setAdminNum(rs.getString("adminNum"));
				admin.setAdminId(rs.getString("adminId"));
				admin.setAdminPassword(rs.getString("adminPass"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminTel(rs.getString("adminTel"));
				admin.setAdminEmail(rs.getString("adminEmail"));
				admin.setAdminGrade(rs.getString("adminGrade"));
				admin.setAdminApv(rs.getString("adminApv"));
			}
		} catch (Exception e) {
			System.out.println("getAdminInfo 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return admin;
	}

	public MainCount getRentalCount() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MainCount mainCount = null;
		String query = "select * from view_rental_count";
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mainCount = new MainCount();
				mainCount.setTodayBrwCount(rs.getString("todaybrw"));
				mainCount.setTodayRevCount(rs.getString("todayrev"));
				mainCount.setTodayOutBrwCount(rs.getString("todayoutbrw"));
				mainCount.setTodayOutRevCount(rs.getString("todayoutrev"));
				mainCount.setProgressBrwCount(rs.getString("progressbrw"));
				mainCount.setProgressRevCount(rs.getString("progressrev"));
				mainCount.setProgressOutBrwCount(rs.getString("progressoutbrw"));
				mainCount.setProgressOutRevCount(rs.getString("progressoutrev"));
			}
		} catch (Exception e) {
			System.out.println("getRentalCount 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return mainCount;
	}

	public ArrayList<Hashtable<String, BoardBean>> getBoardMain() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		Hashtable<String, BoardBean> boardHash = null;
		ArrayList<Hashtable<String, BoardBean>> boardList = new ArrayList<Hashtable<String, BoardBean>>();
		String query = "SELECT * FROM board WHERE boardFlag = 1 ORDER BY boardNum DESC LIMIT 5";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				int count = 1;
				while (rs.next()) {
					boardBean = new BoardBean();
					boardBean.setBoardSubject(rs.getString("boardSubject"));
					boardBean.setBoardDate(rs.getString("boardDate"));
					boardHash = new Hashtable<>();
					boardHash.put("1"+count++, boardBean);
					boardList.add(boardHash);
				}
			} /* 공지사항 끝 */
			
			query = "SELECT * FROM board WHERE boardFlag = 2 and boardSeq = 0 ORDER BY boardNum DESC LIMIT 5";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				int count = 1;
				while (rs.next()) {
					boardBean = new BoardBean();
					boardBean.setBoardSubject(rs.getString("boardSubject"));
					boardBean.setBoardDate(rs.getString("boardDate"));
					boardHash = new Hashtable<>();
					boardHash.put("2"+count++, boardBean);
					boardList.add(boardHash);
				}
			} /* QnA 끝 */
			
			query = "SELECT * FROM board WHERE boardFlag = 3 ORDER BY boardNum DESC LIMIT 5";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				int count = 1;
				while (rs.next()) {
					boardBean = new BoardBean();
					boardBean.setBoardSubject(rs.getString("boardSubject"));
					boardBean.setBoardDate(rs.getString("boardDate"));
					boardHash = new Hashtable<>();
					boardHash.put("3"+count++, boardBean);
					boardList.add(boardHash);
				}
			} /* 희망도서 끝 */
			
			query = "SELECT * FROM board WHERE boardFlag = 4 ORDER BY boardNum DESC LIMIT 5";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				int count = 1;
				while (rs.next()) {
					boardBean = new BoardBean();
					boardBean.setBoardSubject(rs.getString("boardSubject"));
					boardBean.setBoardDate(rs.getString("boardDate"));
					boardHash = new Hashtable<>();
					boardHash.put("4"+count++, boardBean);
					boardList.add(boardHash);
				}
			} /* 자유게시판 끝 */
		} catch (Exception e) {
			System.out.println("getBoardMain 에러 ::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return boardList;
	}

	public ArrayList<BoardBean> getBoard1Main() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		ArrayList<BoardBean> boardList = new ArrayList<>();
		String query = "SELECT * FROM board WHERE boardFlag = 1 ORDER BY boardNum DESC LIMIT 5";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					boardBean = new BoardBean();
					boardBean.setBoardNum(rs.getInt("boardNum"));
					boardBean.setBoardSubject(rs.getString("boardSubject"));
					boardBean.setBoardDate(rs.getString("boardDate"));
					boardList.add(boardBean);
				}
			}
		} catch (Exception e) {
			System.out.println("getBoard1Main 에러 ::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return boardList;
	}
	
	public ArrayList<BoardBean> getBoard2Main() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		ArrayList<BoardBean> boardList = new ArrayList<>();
		String query = "SELECT * FROM board WHERE boardFlag = 2 AND boardSEQ = 0 ORDER BY boardNum DESC LIMIT 5";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					boardBean = new BoardBean();
					boardBean.setBoardNum(rs.getInt("boardNum"));
					boardBean.setBoardSubject(rs.getString("boardSubject"));
					boardBean.setBoardDate(rs.getString("boardDate"));
					boardList.add(boardBean);
				}
			}
		} catch (Exception e) {
			System.out.println("getBoard2Main 에러 ::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return boardList;
	}
	
	public ArrayList<BoardBean> getBoard3Main() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		ArrayList<BoardBean> boardList = new ArrayList<>();
		String query = "SELECT * FROM board WHERE boardFlag = 3 ORDER BY boardNum DESC LIMIT 5";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					boardBean = new BoardBean();
					boardBean.setBoardNum(rs.getInt("boardNum"));
					boardBean.setBoardSubject(rs.getString("boardSubject"));
					boardBean.setBoardDate(rs.getString("boardDate"));
					boardList.add(boardBean);
				}
			}
		} catch (Exception e) {
			System.out.println("getBoard3Main 에러 ::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return boardList;
	}
	
	public ArrayList<BoardBean> getBoard4Main() {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		ArrayList<BoardBean> boardList = new ArrayList<>();
		String query = "SELECT * FROM board WHERE boardFlag = 4 ORDER BY boardNum DESC LIMIT 5";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					boardBean = new BoardBean();
					boardBean.setBoardNum(rs.getInt("boardNum"));
					boardBean.setBoardSubject(rs.getString("boardSubject"));
					boardBean.setBoardDate(rs.getString("boardDate"));
					boardList.add(boardBean);
				}
			}
		} catch (Exception e) {
			System.out.println("getBoardMain 에러 ::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return boardList;
	}
}
