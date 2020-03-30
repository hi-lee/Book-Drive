package book.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import book.dao.admin.BookDAO;
import vo.admin.Book;
import vo.admin.BookRental;

public class BookRevListService {
	public ArrayList<BookRental> selectBookRevList() {
		// TODO Auto-generated method stub
		ArrayList<BookRental> bookRevList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookRevList = bookDAO.getBookRevList();
		if (con != null) close(con);
		return bookRevList;
	}

}
