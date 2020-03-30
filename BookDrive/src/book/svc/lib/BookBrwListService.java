package book.svc.lib;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import book.dao.lib.BookDAO;
import vo.admin.BookRental;

public class BookBrwListService {
	public ArrayList<BookRental> selectBookBrwList(String libCode) {
		// TODO Auto-generated method stub
		ArrayList<BookRental> bookBrwList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookBrwList = bookDAO.getBookBrwList(libCode);
		if (con != null) close(con);
		return bookBrwList;
	}
}
