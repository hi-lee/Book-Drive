package log.svc.lib;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import log.dao.lib.LogDAO;
import vo.admin.BoardBean;
import vo.admin.Book;
import vo.admin.MainCount;

public class MainService {
	public MainCount selectRentalCount(String loginCode) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		MainCount mainCount = null;
		mainCount = logDAO.getRentalCount(loginCode);
		if (con != null) close(con);
		return mainCount;
	}

	public ArrayList<BoardBean> selectBoard1() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		ArrayList<BoardBean> boardList = new ArrayList<>();
		boardList = logDAO.getBoard1Main();
		if (con != null) close(con);
		return boardList;
	}

	public ArrayList<BoardBean> selectBoard2(String loginCode) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		ArrayList<BoardBean> boardList = new ArrayList<>();
		boardList = logDAO.getBoard2Main(loginCode);
		if (con != null) close(con);
		return boardList;
	}

	public ArrayList<BoardBean> selectBoard3(String loginCode) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		ArrayList<BoardBean> boardList = new ArrayList<>();
		boardList = logDAO.getBoard3Main(loginCode);
		if (con != null) close(con);
		return boardList;
	}

	public ArrayList<BoardBean> selectBoard4() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		ArrayList<BoardBean> boardList = new ArrayList<>();
		boardList = logDAO.getBoard4Main();
		if (con != null) close(con);
		return boardList;
	}

	public ArrayList<Book> selectBook1(String loginCode) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		ArrayList<Book> bookList = new ArrayList<>();
		bookList = logDAO.getBook1Main(loginCode);
		if (con != null) close(con);
		return bookList;
	}

	public ArrayList<Book> selectBook2(String loginCode) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		ArrayList<Book> bookList = new ArrayList<>();
		bookList = logDAO.getBook2Main(loginCode);
		if (con != null) close(con);
		return bookList;
	}

}
