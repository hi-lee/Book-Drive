package board.svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import board.dao.admin.BoardDAO;
import vo.admin.BoardBean;

public class BoardDetailService {
	public BoardBean selectBoardDetail(int boardNum) {
		// TODO Auto-generated method stub
		BoardBean boardBean = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		/* 관리자는 조회수 업데이트를 할 필요가 없음.
		int updateCount = boardDAO.updateReadCount(boardNum);
		
		if (updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		*/
		boardBean = boardDAO.selectDetailBoard(boardNum);
		if (con != null) close(con);
		return boardBean;
	}
	
	public BoardBean selectBoardReplyDetail(int boardNum) {
		// TODO Auto-generated method stub
		BoardBean boardBean = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		/* 관리자는 조회수 업데이트를 할 필요가 없음.
		int updateCount = boardDAO.updateReadCount(boardNum);
		
		if (updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		*/
		boardBean = boardDAO.selectDetailBoardReply(boardNum);
		if (con != null) close(con);
		return boardBean;
	}
	
	public BoardBean getBoardBean(int boardNum) {
		// TODO Auto-generated method stub
		BoardBean boardBean = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boardBean = boardDAO.selectDetailBoard(boardNum);
		if (con != null) close(con);
		return boardBean;
	}

}
