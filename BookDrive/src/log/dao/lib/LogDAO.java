package log.dao.lib;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.sql.DataSource;

import vo.admin.Admin;
import vo.admin.BoardBean;
import vo.admin.Book;
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
			pstmt = con.prepareStatement("select * from admin where adminId = ? and adminGrade = 'L' and adminApv = 'YES'");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("logDAO 조회성공 : " + rs.getString("adminId"));
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
			System.out.println("getAdminInfo 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return admin;
	}

	public MainCount getRentalCount(String loginCode) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MainCount mainCount = new MainCount();
		String query = "SELECT COUNT(*) as todaybrw FROM rentalIdv WHERE renFlag = 'brw' AND renBrwDate = CURDATE() and libCode = '" + loginCode + "'";
		
		try {
			//오늘대출
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mainCount.setTodayBrwCount(rs.getString("todaybrw"));
			}
			//오늘예약
			query = "SELECT COUNT(*) as todayrev FROM rentalIdv WHERE renFlag = 'rev' AND renRevDate = CURDATE() and libCode = '" + loginCode + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mainCount.setTodayRevCount(rs.getString("todayrev"));
			}
			//오늘관외대출
			query = "SELECT COUNT(*) as todayoutbrw FROM rentalIdv WHERE renFlag = 'outbrw' AND renRevDate = CURDATE() and libCode = '" + loginCode + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mainCount.setTodayOutBrwCount(rs.getString("todayoutbrw"));
			}
			//오늘관외예약
			query = "SELECT COUNT(*) as todayoutrev FROM rentalIdv WHERE renFlag = 'outrev' AND renRevDate = CURDATE() and libCode = '" + loginCode + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mainCount.setTodayOutRevCount(rs.getString("todayoutrev"));
			}
			//진행대출
			query = "SELECT COUNT(*) as progressbrw FROM rentalIdv WHERE renFlag = 'brw' AND renIdvDelFlag = 0 and libCode = '" + loginCode + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mainCount.setProgressBrwCount(rs.getString("progressbrw"));
			}
			//진행예약
			query = "SELECT COUNT(*) as progressrev FROM rentalIdv WHERE renFlag = 'rev' AND renIdvDelFlag = 0 and libCode = '" + loginCode + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mainCount.setProgressRevCount(rs.getString("progressrev"));
			}
			//진행관외대출
			query = "SELECT COUNT(*) as progressoutbrw FROM rentalIdv WHERE renFlag = 'out' AND renIdvDelFlag = 0 and libCode = '" + loginCode + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mainCount.setProgressOutBrwCount(rs.getString("progressoutbrw"));
			}
			//진행관외예약
			query = "SELECT COUNT(*) as progressoutrev FROM rentalIdv WHERE renFlag = 'outrev' AND renIdvDelFlag = 0 and libCode = '" + loginCode + "'";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
	
	public ArrayList<BoardBean> getBoard2Main(String loginCode) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		ArrayList<BoardBean> boardList = new ArrayList<>();
		String query = "SELECT * FROM board WHERE boardFlag = 2 AND libCode = '" + loginCode + "' and boardSEQ = 0 ORDER BY boardNum DESC LIMIT 5";
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
	
	public ArrayList<BoardBean> getBoard3Main(String loginCode) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;
		ArrayList<BoardBean> boardList = new ArrayList<>();
		String query = "SELECT * FROM board WHERE boardFlag = 3 and libCode = '" + loginCode + "' ORDER BY boardNum DESC LIMIT 5";
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

	public ArrayList<Book> getBook1Main(String loginCode) { //신착도서
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Book book = null;
		ArrayList<Book> bookList = new ArrayList<>();
		String query = "SELECT bookNum, bookName, bookImage FROM bookInfo WHERE libCode = ? ORDER BY bookRegDate DESC LIMIT 5";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, loginCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					book = new Book();
					book.setBookImage(rs.getString("bookImage"));
					book.setBookName(rs.getString("bookName"));
					book.setBookNum(rs.getString("bookNum"));
					bookList.add(book);
				}
			}
		} catch (Exception e) {
			System.out.println("getBook1Main 에러 ::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return bookList;
	}

	public ArrayList<Book> getBook2Main(String loginCode) { //인기도서
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Book book = null;
		ArrayList<Book> bookList = new ArrayList<>();
		String query = "SELECT a.renIdvNum, a.libCode, COUNT(b.bookName) AS todaycount, b.bookNum, b.bookName, b.bookImage" + 
						" FROM rentalIdv AS a JOIN bookInfo AS b ON a.bookNum = b.bookNum WHERE a.libCode = ?" + 
						" GROUP BY b.bookName ORDER BY todaycount DESC LIMIT 5";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, loginCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					book = new Book();
					book.setBookImage(rs.getString("bookImage"));
					book.setBookName(rs.getString("bookName"));
					book.setBookNum(rs.getString("bookNum"));
					bookList.add(book);
				}
			}
		} catch (Exception e) {
			System.out.println("getBook2Main 에러 ::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return bookList;
	}
}
