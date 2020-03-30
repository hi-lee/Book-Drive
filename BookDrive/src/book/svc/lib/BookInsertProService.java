package book.svc.lib;

import static db.JdbcUtil.*;

import java.sql.Connection;

import book.dao.lib.BookDAO;
import vo.admin.Book;

public class BookInsertProService {
	public boolean insertBook(Book book) {
		// TODO Auto-generated method stub
		int insertCount = 0;
		boolean isInsertCheck = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		insertCount = bookDAO.insertBook(book);
		
		if (insertCount > 0) {
			isInsertCheck = true;
			commit(con);
		} else {
			rollback(con);
		}
		if (con != null) close(con);
		return isInsertCheck;
	}

}
