package book.svc;

import book.vo.Book;
import book.vo.RentalIdv;
import dao.BookDAO;
import static db.JdbcUtil.*;
import java.sql.*;

public class BookDetailSvc {

	public Book getBook(String bookNum) {
		// TODO Auto-generated method stub
		Book book = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		book = bookDAO.selectBook(bookNum);
		close(con);
		return book;
	}

	public RentalIdv getRental(String bookNum) {
		// TODO Auto-generated method stub
		RentalIdv rentalIdv = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		rentalIdv = bookDAO.getRentalIdv(bookNum);
		close(con);
		return rentalIdv;
		
	}

}
