package book.svc;

import java.sql.Connection;
import java.util.ArrayList;

import static db.JdbcUtil.*;
import book.vo.Cart;
import dao.BookDAO;

public class BookCartListSvc {

	public ArrayList<Cart> getCartList(String memIndex) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		ArrayList<Cart> cartList = bookDAO.selectCartList(memIndex);
		close(con);
		return cartList;
	}

}
