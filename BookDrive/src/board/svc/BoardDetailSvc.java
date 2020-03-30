package board.svc;

import java.sql.Connection;
import java.util.ArrayList;

import board.vo.Board;
import dao.BoardDAO;
import vo.admin.BoardBean;

import static db.JdbcUtil.*;

public class BoardDetailSvc {

	public Board getBoard(int boardNum) {
		// TODO Auto-generated method stub
		Board board = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateRCount(boardNum); //조회수 1증
		
		if(updateCount>0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		board = boardDAO.selectBoard(boardNum);
		close(con);
		return board;
	}

	public ArrayList<Board> getBoardNum(int boardNum) { //이전글, 다음글
		// TODO Auto-generated method stub
		ArrayList<Board> list = new ArrayList<>();
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		list = boardDAO.getBoardNum(boardNum);
		close(con);
		return list;
	}
	
	public Board selectBoardReplyDetail(int boardNum) { //QnA 답글
		// TODO Auto-generated method stub
		Board boardBean = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		boardBean = boardDAO.selectDetailBoardReply(boardNum);
		if (con != null) close(con);
		return boardBean;
	}
}
