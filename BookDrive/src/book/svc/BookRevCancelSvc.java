package book.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;
import dao.BookDAO;

public class BookRevCancelSvc {

	public boolean cancelState(int memIndex, String bookNum, String state) {
		// TODO Auto-generated method stub
		boolean isModifySuccess = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int updateCount = bookDAO.revCancelState(memIndex, bookNum, state);
		
		if(updateCount > 0) {
			commit(con);
			isModifySuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isModifySuccess;
	}

}
