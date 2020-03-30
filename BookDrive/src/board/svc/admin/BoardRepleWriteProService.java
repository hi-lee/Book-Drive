package board.svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import board.dao.admin.BoardDAO;
import vo.admin.BoardBean;

public class BoardRepleWriteProService {
	public boolean insertReply(BoardBean boardBean, int boardNum) {
		// TODO Auto-generated method stub
		boolean isReplySuccess = false;
		int insertCount = 0;
		int updateCount = 0; //답글작성이 성공하면 부모 글의 ReplyFlag값도 변경해주어야 함
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		insertCount = boardDAO.insertReplyBoardBean(boardBean, boardNum);
		
		if (insertCount > 0) {
			updateCount = boardDAO.updateReplyFlag(boardNum);
			if (updateCount > 0) {
				commit(con);
				isReplySuccess = true;
			} else {
				rollback(con);
			}
		}
		
		
		if (con != null) close(con);
		return isReplySuccess;
	}

}
