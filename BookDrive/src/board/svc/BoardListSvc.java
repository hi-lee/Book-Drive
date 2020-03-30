package board.svc;

import java.sql.Connection;
import java.util.ArrayList;
import static db.JdbcUtil.*;
import board.vo.Board;
import dao.BoardDAO;

public class BoardListSvc {

	public int getListCount(String kind, String keyword, String flag) {
		// TODO Auto-generated method stub
		int listCount = 0;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectListCount(kind, keyword, flag);
		close(con);
		return listCount;
	}

	public ArrayList<Board> getBoardList(int nowPage, int limit, String kind, String keyword, String flag, String os) {
		// TODO Auto-generated method stub
		ArrayList<Board> boardList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boardList = boardDAO.selectBoardList(nowPage, limit, kind, keyword, flag, os);
		close(con);
		return boardList;
	}

}
