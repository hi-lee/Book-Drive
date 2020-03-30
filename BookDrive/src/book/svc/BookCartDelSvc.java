package book.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.BookDAO;

public class BookCartDelSvc {

	public boolean delCart(String memIndex, String[] cartNum) {
		// TODO Auto-generated method stub
		boolean isDelSuccess = false;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		int delCount = bookDAO.delCartList(memIndex, cartNum);

		if (delCount > 0) {
			commit(con);
			isDelSuccess = true;
		} else {
			rollback(con);
		}
		close(con);
		return isDelSuccess;
	}
}
