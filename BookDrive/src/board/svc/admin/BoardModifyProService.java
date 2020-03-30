package board.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import board.dao.admin.BoardDAO;
import vo.admin.BoardBean;

public class BoardModifyProService {
	public boolean updateBoard(BoardBean boardBean) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boolean isUpdateCheck = false;
//		int insertCount = boardDAO.insertNotice(boardBean);
		int insertCount = boardDAO.updateBoard(boardBean);
//		int selectLastInsert = boardDAO.selectLastInsert();
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
