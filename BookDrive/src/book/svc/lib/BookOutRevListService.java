package book.svc.lib;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import book.dao.lib.BookDAO;
import vo.admin.Book;
import vo.admin.BookRental;

public class BookOutRevListService {
	public ArrayList<BookRental> selectBookRevList(String libCode) {
		// TODO Auto-generated method stub
		ArrayList<BookRental> bookRevList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookRevList = bookDAO.getBookOutRevList(libCode);
		if (con != null) close(con);
		return bookRevList;
	}

}
