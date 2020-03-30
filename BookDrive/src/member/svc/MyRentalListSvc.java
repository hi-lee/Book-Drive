package member.svc;

import java.sql.Connection;
import java.util.ArrayList;

import book.vo.RentalIdv;
import dao.BookDAO;

import static db.JdbcUtil.*;
public class MyRentalListSvc {

	public ArrayList<RentalIdv> getRentalBookList(String memIndex) {

		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		ArrayList<RentalIdv> rentalList = bookDAO.getRentalList(memIndex);
		if(con != null) close(con);
		return rentalList;
	}
}
