package board.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.BoardDAO;
import board.vo.Board;

public class BoardWriteSvc {
	public int insertBoard(Board boardBean) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int insertCount = boardDAO.insertNotice(boardBean);
		int selectLastInsert = boardDAO.selectLastInsert();
		if (insertCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		if (con != null) close(con);
		return selectLastInsert;
	}

}
