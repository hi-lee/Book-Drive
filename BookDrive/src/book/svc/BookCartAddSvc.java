package book.svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import book.vo.Book;
import dao.BookDAO;

public class BookCartAddSvc {

	public boolean addCart(String memIndex, Book bookCart) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		boolean isInsertSuccess = false;
		int insertCount = bookDAO.addCart(memIndex, bookCart);
		if (insertCount > 0) {
			commit(con);
			isInsertSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isInsertSuccess;
	}
}
