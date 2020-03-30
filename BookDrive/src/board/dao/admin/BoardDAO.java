package board.dao.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import board.vo.Board;
import vo.admin.BoardBean;
import vo.admin.Book;

public class BoardDAO {
	DataSource ds;
	Connection con;
	private static BoardDAO boardDAO;
	
	private BoardDAO() {}
	
	public static BoardDAO getInstance() {
		if (boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public int insertNotice(BoardBean boardBean) {
		// TODO Auto-generated method stub
		System.out.println(boardBean.toString());
		int insertCount = 0;
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxnum = 0;
		String query = "insert into board values (" +
						"0, " + //boardNum 
						"0, " + //memIndex
						"?, " + //adminNum 1
						"0, " + //libCode
						"?, " + //boardSubject 2
						"?, " + //boardPass 3
						"?, " + //boardContent 4
						"1, " + //boardRCount
						"?, " + //boardDate 5
						"?, " + //boardWriter 6
						"?, " + //boardFlag 7
						"?, " + //boardFile 8
						"?, " + //boardWriterFlag 9
						"0, " + //boardReplyFlag
						"?, " + //boardRef 10
						"0, " + //boardLev
						"0)";  //boardSeq
		try {
			pstmt = con.prepareStatement("select max(boardNum) from board");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				maxnum = rs.getInt(1) + 1;
			}
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, boardBean.getAdminNum());
			pstmt.setString(2, boardBean.getBoardSubject());
			pstmt.setString(3, boardBean.getBoardPass());
			pstmt.setString(4, boardBean.getBoardContent());
			pstmt.setString(5, format.format(new Date()));
			pstmt.setString(6, boardBean.getBoardWriter());
			pstmt.setString(7, boardBean.getBoardFlag());
			pstmt.setString(8, boardBean.getBoardFile());
			pstmt.setString(9, boardBean.getBoardWriterFlag());
			pstmt.setInt(10, maxnum);
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertNotice 에러 :::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return insertCount;
	}

	public int selectLastInsert() { //최근 등록한 글 번호 가져옴
		// TODO Auto-generated method stub
		int lastInsertId = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select last_insert_id()");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				lastInsertId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("selectLastInsert 에러 :::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return lastInsertId;
	}

	public int updateReadCount(int boardNum) { //조회수 업데이트
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql = "update board set boardRCount = " + "boardRCount +1 where boardNum = " + boardNum;
		
		try {
			pstmt = con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate 에러 : " + ex);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		
		return updateCount;
	}

	public BoardBean selectDetailBoard(int boardNum) { //글 상세보기
		// TODO Auto-generated method stub
		BoardBean boardBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where boardNum = " + boardNum;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardBean = new BoardBean();
				boardBean.setBoardSubject(rs.getString("boardSubject"));
				boardBean.setBoardPass(rs.getString("boardPass"));
				boardBean.setBoardContent(rs.getString("boardContent"));
				boardBean.setBoardRCount(rs.getString("boardRCount"));
				boardBean.setBoardDate(rs.getString("boardDate"));
				boardBean.setBoardWriter(rs.getString("boardWriter"));
				boardBean.setBoardFlag(rs.getString("boardFlag"));
				boardBean.setBoardFile(rs.getString("boardFile"));
				boardBean.setBoardWriterFlag(rs.getString("boardWriterFlag"));
				boardBean.setBoardRef(rs.getString("boardRef"));
				boardBean.setBoardLev(rs.getString("boardLev"));
				boardBean.setBoardSeq(rs.getString("boardSeq"));
			}
		} catch (Exception e) {
			System.out.println("selectDetailBoard 에러 :::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return boardBean;
	}
	
	public BoardBean selectDetailBoardReply(int boardNum) { //답글 상세보기
		// TODO Auto-generated method stub
		BoardBean boardBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where boardRef = " + boardNum + " and boardSeq = 1";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardBean = new BoardBean();
				boardBean.setBoardNum(Integer.parseInt(rs.getString("boardNum")));
				boardBean.setBoardSubject(rs.getString("boardSubject"));
				boardBean.setBoardPass(rs.getString("boardPass"));
				boardBean.setBoardContent(rs.getString("boardContent"));
				boardBean.setBoardRCount(rs.getString("boardRCount"));
				boardBean.setBoardDate(rs.getString("boardDate"));
				boardBean.setBoardWriter(rs.getString("boardWriter"));
				boardBean.setBoardFlag(rs.getString("boardFlag"));
				boardBean.setBoardFile(rs.getString("boardFile"));
				boardBean.setBoardWriterFlag(rs.getString("boardWriterFlag"));
				boardBean.setBoardRef(rs.getString("boardRef"));
				boardBean.setBoardLev(rs.getString("boardLev"));
				boardBean.setBoardSeq(rs.getString("boardSeq"));
			}
		} catch (Exception e) {
			System.out.println("selectDetailBoardReply 에러 :::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return boardBean;
	}
	
	public int selectBoardListCount(String kind, String keyword, String flag) { //게시판 카운트
		// TODO Auto-generated method stub
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT count(*) from board where";
		if (kind.equals("title")) query += " boardSubject like ";
		else if (kind.equals("writer")) query += " boardWriter like ";
		else if (kind.equals("content")) query += " boardContent like ";
		query += "'%" + keyword + "%'";
		query += " and boardFlag = " + flag;
		query += " and boardLev = 0 and boardSeq = 0";
		System.out.println("게시판 카운트 쿼리 : " + query);
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getBoardListCount 에러 : " + ex);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		
		return listCount;
	}

	public ArrayList<BoardBean> getBoardList(int nowPage, int limit, String kind, String keyword, String flag, String os) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BoardBean> boardList = new ArrayList<>();
		BoardBean boardBean = null;
		int startrow = (nowPage - 1) * limit; //읽기 시작할 row 번호(넘어온 페이지값에 limit를 곱한 값이 되어야 함)
		String query = "select * from (" + 
						"SELECT @rownum := @rownum + 1 AS rownum, a.*" + 
						" FROM board as a, (SELECT @rownum:=0) tmp where";
						if (kind.equals("title")) query += " boardSubject like ";
						else if (kind.equals("writer")) query += " boardWriter like ";
						else if (kind.equals("content")) query += " boardContent like ";
						query += "'%" + keyword + "%'";
						query += " and boardFlag = " + flag;
						query += " and boardLev = 0 and boardSeq = 0) as a";
//						query += " and boardFlag = " + flag + ") as a";
//		query += " where rownum >= " + (startrow + 1) + " and rownum <= " + (nowPage * limit);
		query += " order by a.boardNum " + os + " limit " + startrow + ", " + limit;
		System.out.println("게시판 리스트 Query : " + query);
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				boardBean = new BoardBean();
				boardBean.setRowNum((int) Math.floor(rs.getDouble("rownum")));
				boardBean.setBoardNum(rs.getInt("boardNum"));
				boardBean.setBoardSubject(rs.getString("boardSubject"));
				boardBean.setBoardPass(rs.getString("boardPass"));
				boardBean.setBoardContent(rs.getString("boardContent"));
				boardBean.setBoardRCount(rs.getString("boardRCount"));
				boardBean.setBoardDate(rs.getString("boardDate"));
				boardBean.setBoardWriter(rs.getString("boardWriter"));
				boardBean.setBoardFlag(rs.getString("boardFlag"));
				boardBean.setBoardFile(rs.getString("boardFile"));
				boardBean.setBoardWriterFlag(rs.getString("boardWriterFlag"));
				boardBean.setBoardReplyFlag(rs.getString("boardReplyFlag"));
				boardBean.setBoardRef(rs.getString("boardRef"));
				boardBean.setBoardLev(rs.getString("boardLev"));
				boardBean.setBoardSeq(rs.getString("boardSeq"));
				boardList.add(boardBean);
			}
		} catch (Exception e) {
			System.out.println("getBoardList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return boardList;
	}

	public int insertReplyBoardBean(BoardBean boardBean, int num) { //답글 달기
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String board_max_sql = "select max(boardNum) from board";
		String sql = "";
//		int num = 0;
		int insertCount = 0;
		int re_ref = Integer.parseInt(boardBean.getBoardRef());
		int re_lev = Integer.parseInt(boardBean.getBoardLev());
		int re_seq = Integer.parseInt(boardBean.getBoardSeq());
		
		try {
//			pstmt = con.prepareStatement(board_max_sql);
//			rs = pstmt.executeQuery();
//			if (rs.next()) num = rs.getInt(1) + 1;
//			else num = 1;
			sql = "update board set boardSeq = boardSeq +1 where boardRef = ? ";
			sql += "and boardSeq > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			int updateCount = pstmt.executeUpdate();
			
			if (updateCount > 0) {
				commit(con);
			}
			
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;
			//sql = "insert into board (board_num, board_name, board_pass, board_subject,";
			//sql += "board_content, board_file, board_re_ref, board_re_lev, board_re_seq,";
			//sql += "board_readcount, board_date values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now())";
			sql = "insert into board values (" +
					"0, " + //boardNum 
					"0, " + //memIndex
					"?, " + //adminNum 1
					"0, " + //libCode
					"?, " + //boardSubject 2
					"?, " + //boardPass 3
					"?, " + //boardContent 4
					"1, " + //boardRCount
					"now(), " + //boardDate
					"?, " + //boardWriter 5
					"?, " + //boardFlag 6
					"?, " + //boardFile 7
					"?, " + //boardWriterFlag 8
					"?, " + //boardReplyFlag 9
					"?, " + //boardRef 10
					"?, " + //boardLev 11
					"?)";  //boardSeq 12
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardBean.getAdminNum());
			pstmt.setString(2, boardBean.getBoardSubject());
			pstmt.setString(3, boardBean.getBoardPass());
			pstmt.setString(4, boardBean.getBoardContent());
			pstmt.setString(5, boardBean.getBoardWriter());
			pstmt.setString(6, boardBean.getBoardFlag());
			pstmt.setString(7, boardBean.getBoardFile());
			pstmt.setString(8, boardBean.getBoardWriterFlag());
			pstmt.setString(9, "0"); //답글에는 답글유무 플래그가 필요없음
			pstmt.setInt(10, re_ref);
			pstmt.setInt(11, re_lev);
			pstmt.setInt(12, re_seq);
			insertCount = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("insertRepleBoardBean 에러 : " + ex);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		
		return insertCount;
	}

	public int updateBoard(BoardBean boardBean) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String query = "update board set boardSubject = ?, boardPass = ?, boardContent = ? where boardNum = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, boardBean.getBoardSubject());
			pstmt.setString(2, boardBean.getBoardPass());
			pstmt.setString(3, boardBean.getBoardContent());
			pstmt.setInt(4, boardBean.getBoardNum());
			updateCount = pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("setUpdateBoard 에러 : " + ex);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		return updateCount;
	}

	public int updateReplyFlag(int boardNum) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String query = "update board set boardReplyFlag = ? where boardNum = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "1");
			pstmt.setInt(2, boardNum);
			updateCount = pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("setUpdateBoardReplyFlag 에러 : " + ex);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		return updateCount;
	}

	public int DeleteBoard(String boardNum) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		String sql = "delete from board where boardNum = ?";
		int deleteCount = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardNum);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("boardDelete 에러 : " + ex);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		
		return deleteCount;
	}
}
