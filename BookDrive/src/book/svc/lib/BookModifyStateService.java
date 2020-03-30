package book.svc.lib;

import book.dao.lib.BookDAO;
import static db.JdbcUtil.*;

import java.sql.Connection;

public class BookModifyStateService {
	public boolean updateBookState(String bookNum, String updateState) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		boolean isUpdateCheck = false;
		int updateCount = bookDAO.updateBookState(bookNum, updateState);
		
		if (updateCount > 0) {
			isUpdateCheck = true;
			commit(con);
		} else {
			rollback(con);
		}
		if (con != null) close(con);
		return isUpdateCheck;
	}
}
