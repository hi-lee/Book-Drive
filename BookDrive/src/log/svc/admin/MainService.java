package log.svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

import log.dao.admin.LogDAO;
import vo.admin.Admin;
import vo.admin.BoardBean;
import vo.admin.MainCount;

public class MainService {
	public MainCount selectRentalCount() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		MainCount mainCount = null;
		mainCount = logDAO.getRentalCount();
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

	public ArrayList<BoardBean> selectBoard2() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		ArrayList<BoardBean> boardList = new ArrayList<>();
		boardList = logDAO.getBoard2Main();
		if (con != null) close(con);
		return boardList;
	}

	public ArrayList<BoardBean> selectBoard3() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		LogDAO logDAO = LogDAO.getInstance();
		logDAO.setConnection(con);
		ArrayList<BoardBean> boardList = new ArrayList<>();
		boardList = logDAO.getBoard3Main();
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

}
