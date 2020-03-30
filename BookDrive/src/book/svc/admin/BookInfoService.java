package book.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import book.dao.admin.BookDAO;
import vo.admin.BookRentalInfo;

public class BookInfoService {
	public ArrayList<BookRentalInfo> getBookInfo(String bookNum) {
		// TODO Auto-generated method stub
		ArrayList<BookRentalInfo> bookRentalInfo = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookRentalInfo = bookDAO.getBookInfo(bookNum);
		if (con != null) close(con);
		return bookRentalInfo;
	}

}
