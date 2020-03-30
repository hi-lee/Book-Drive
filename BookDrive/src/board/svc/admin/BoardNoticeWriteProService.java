package board.svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import board.dao.admin.BoardDAO;
import vo.admin.BoardBean;

public class BoardNoticeWriteProService {
	public int selectBoardNotice(BoardBean boardBean) {
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
