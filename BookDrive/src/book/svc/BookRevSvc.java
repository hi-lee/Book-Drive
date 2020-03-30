package book.svc;

import java.sql.Connection;

import book.vo.Book;
import dao.BookDAO;
import static db.JdbcUtil.*;

public class BookRevSvc {

	public boolean insertState(String memIndex, String bookNum, String libCode, String state, String usage) {
		// TODO Auto-generated method stub
		boolean isModifySuccess = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int insertCount = bookDAO.insertState(memIndex, bookNum, libCode, state, usage);

		if (insertCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}
	
	

}
