package board.svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import board.dao.admin.BoardDAO;

public class BoardDeleteProService {
	public boolean DeleteBoard(String boardNum) {
		// TODO Auto-generated method stub
		int deleteCount = 0;
		boolean isDeleteCheck = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		deleteCount = boardDAO.DeleteBoard(boardNum);
		
		if (deleteCount > 0) {
			isDeleteCheck = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		if (con != null) close(con);
		return isDeleteCheck;
	}

	public boolean deleteReplyBoard(String cboardNum) {
		// TODO Auto-generated method stub
		int deleteCount = 0;
		boolean isDeleteReplyCheck = false;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		deleteCount = boardDAO.DeleteBoard(cboardNum);
		
		if (deleteCount > 0) {
			isDeleteReplyCheck = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		if (con != null) close(con);
		return isDeleteReplyCheck;
	}

}
