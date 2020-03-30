package book.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import book.dao.admin.BookDAO;
import vo.admin.BookRental;

public class BookBrwListService {
	public ArrayList<BookRental> selectBookBrwList() {
		// TODO Auto-generated method stub
		ArrayList<BookRental> bookBrwList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookBrwList = bookDAO.getBookBrwList();
		if (con != null) close(con);
		return bookBrwList;
	}
}
