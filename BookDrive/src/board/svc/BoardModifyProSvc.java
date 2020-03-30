package board.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import board.vo.Board;
import vo.admin.BoardBean;

public class BoardModifyProSvc {
	public boolean updateBoard(Board boardBean) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boolean isUpdateCheck = false;
		int insertCount = boardDAO.updateBoard(boardBean);
		if (insertCount > 0) {
			isUpdateCheck = true;
			commit(con);
		} else {
			rollback(con);
		}
		if (con != null) close(con);
		return isUpdateCheck;
	}
}
