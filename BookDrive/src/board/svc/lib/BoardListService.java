package board.svc.lib;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import board.dao.lib.BoardDAO;
import vo.admin.BoardBean;
import vo.admin.Book;

public class BoardListService {
	public int getListCount(String kind, String keyword, String flag, String libCode) {
		// TODO Auto-generated method stub
		int listCount = 0;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		listCount = boardDAO.selectBoardListCount(kind, keyword, flag, libCode); //전체 글 갯수를 가져옴
		if (con != null) close(con);
		return listCount;
	}

	public ArrayList<BoardBean> selectBoardList(int nowPage, int limit, String kind, String keyword, String flag, String os, String libCode) {
		// TODO Auto-generated method stub
		ArrayList<BoardBean> boardList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boardList = boardDAO.getBoardList(nowPage, limit, kind, keyword, flag, os, libCode);
		if (con != null) close(con);
		return boardList;
	}

}
