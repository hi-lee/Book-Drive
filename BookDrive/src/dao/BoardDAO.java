package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import board.vo.Board;
import vo.admin.BoardBean;

import static db.JdbcUtil.*;

public class BoardDAO {
	Connection con;
	private static BoardDAO boardDAO;

	public static BoardDAO getInstance() {
		// TODO Auto-generated method stub
		if (boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}

	public int selectListCount(String kind, String keyword, String flag) {
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
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
			System.out.println("sql :  " + query);

		} catch (Exception e) {
			System.out.println("listCount Err  :  " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Board> selectBoardList(int nowPage, int limit, String kind, String keyword, String flag, String os) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> boardList = new ArrayList<Board>();
		Board board = null;
		int startRow = (nowPage - 1) * limit; //읽기 시작할 row 번호(넘어온 페이지값에 limit를 곱한 값이 되어야 함)
		String query = "select * from (" + 
				"SELECT @rownum := @rownum + 1 AS rownum, a.*" + 
				" FROM board as a, (SELECT @rownum:=0) tmp where";
				if (kind.equals("title")) query += " boardSubject like ";
				else if (kind.equals("writer")) query += " boardWriter like ";
				else if (kind.equals("content")) query += " boardContent like ";
				query += "'%" + keyword + "%'";
				query += " and boardFlag = " + flag;
				query += " and boardLev = 0 and boardSeq = 0) as a";
				query += " order by a.boardNum " + os + " limit " + startRow + ", " + limit;
		
		
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				board = new Board();
				board.setBoardNum(rs.getInt("boardNum"));
				board.setMemIndex(rs.getInt("memIndex"));
				board.setBoardSubject(rs.getString("boardSubject"));
				board.setBoardPass(rs.getString("boardPass"));
				board.setBoardContent(rs.getString("boardContent"));
				board.setBoardRCount(rs.getInt("boardRCount"));
				board.setBoardDate(rs.getString("boardDate"));
				board.setBoardWriter(rs.getString("boardWriter"));
				board.setBoardFlag(rs.getString("boardFlag"));
				board.setBoardFile(rs.getString("boardFile"));
				board.setBoardWriterFlag(rs.getString("boardWriterFlag"));
				board.setBoardReplyFlag(rs.getString("boardReplyFlag"));
				board.setBoardRef(rs.getInt("boardRef"));
				board.setBoardLev(rs.getInt("boardLev"));
				board.setBoardSeq(rs.getInt("boardSeq"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getBoardList 에러 : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return boardList;
	}

	public int updateRCount(int boardNum) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String count_sql = "update board set boardRCount =" + "boardRCount+1 where boardNum = " + boardNum;
		try {
			pstmt = con.prepareStatement(count_sql);
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("updateRCount Err   :  " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	public Board selectBoard(int boardNum) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;

		try {
			pstmt = con.prepareStatement("select * from board where boardNum = ? ");
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new Board();
				board.setBoardNum(rs.getInt("boardNum"));
				board.setBoardSubject(rs.getString("boardSubject"));
				board.setBoardPass(rs.getString("boardPass"));
				board.setBoardContent(rs.getString("boardContent"));
				board.setBoardRCount(rs.getInt("boardRCount"));
				board.setBoardDate(rs.getString("boardDate"));
				board.setBoardFlag(rs.getString("boardFlag"));
				board.setBoardWriter(rs.getString("boardWriter"));
				board.setBoardFile(rs.getString("boardFile"));
				board.setBoardWriterFlag(rs.getString("boardWriterFlag"));
				board.setBoardRef(rs.getInt("boardRef"));
				board.setBoardLev(rs.getInt("boardLev"));
				board.setBoardSeq(rs.getInt("boardSeq"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getBoard Err   :  " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	}

	public int insertNotice(Board boardBean) { //글작성
		// TODO Auto-generated method stub
		System.out.println(boardBean.toString());
		int insertCount = 0;
		SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int maxnum = 0;
		String query = "insert into board values (" +
						"0, " + //boardNum 
						"?, " + //memIndex 1
						"0, " + //adminNum
						"0, " + //libCode
						"?, " + //boardSubject 2
						"?, " + //boardPass 3
						"?, " + //boardContent 4
						"0, " + //boardRCount
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
			pstmt.setInt(1, boardBean.getMemIndex());
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

	public int selectLastInsert() { //최근에 등록한 글 번호를 가져옴
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

	public ArrayList<Board> getBoardNum(int boardNum) { //이전글, 다음글 가져오기
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> list = new ArrayList<>();
		Board board = null;
		String query = "SELECT boardNum, boardSubject, boardDate FROM board" + 
						" WHERE boardNum IN" + 
						" ((SELECT boardNum FROM board WHERE boardNum < " + boardNum + " ORDER BY boardNum DESC LIMIT 1)," + 
						" (SELECT boardNum FROM board WHERE boardNum > " + boardNum + " ORDER BY boardNum LIMIT 1))";
		System.out.println("이전글 다음글 : " + query);
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					board = new Board();
					board.setBoardNum(rs.getInt("boardNum"));
					board.setBoardSubject(rs.getString("boardSubject"));
					board.setBoardDate(rs.getString("boardDate"));
					list.add(board);
				}
			}
		} catch (Exception e) {
			System.out.println("getBoardNum 에러 :::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return list;
	}
	
	public int updateBoard(Board boardBean) { //게시판 글수정
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String query = "update board set boardSubject = ?, boardContent = ? where boardNum = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, boardBean.getBoardSubject());
			pstmt.setString(2, boardBean.getBoardContent());
			pstmt.setInt(3, boardBean.getBoardNum());
			updateCount = pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("UpdateBoard 에러 ::::: " + ex);
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
	
	public Board selectDetailBoardReply(int boardNum) { //QnA답글 상세보기
		// TODO Auto-generated method stub
		Board boardBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where boardRef = " + boardNum + " and boardSeq = 1";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				boardBean = new Board();
				boardBean.setBoardNum(Integer.parseInt(rs.getString("boardNum")));
				boardBean.setBoardSubject(rs.getString("boardSubject"));
				boardBean.setBoardPass(rs.getString("boardPass"));
				boardBean.setBoardContent(rs.getString("boardContent"));
				boardBean.setBoardRCount(rs.getInt("boardRCount"));
				boardBean.setBoardDate(rs.getString("boardDate"));
				boardBean.setBoardWriter(rs.getString("boardWriter"));
				boardBean.setBoardFlag(rs.getString("boardFlag"));
				boardBean.setBoardFile(rs.getString("boardFile"));
				boardBean.setBoardWriterFlag(rs.getString("boardWriterFlag"));
				boardBean.setBoardRef(rs.getInt("boardRef"));
				boardBean.setBoardLev(rs.getInt("boardLev"));
				boardBean.setBoardSeq(rs.getInt("boardSeq"));
			}
		} catch (Exception e) {
			System.out.println("selectDetailBoardReply 에러 :::: " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return boardBean;
	}
}
