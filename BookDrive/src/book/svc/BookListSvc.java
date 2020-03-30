package book.svc;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import book.vo.Book;
import dao.BookDAO;
public class BookListSvc {

	public int getListCount(String search, String value, String usage) {
		// TODO Auto-generated method stub
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		listCount = bookDAO.selectCount(search, value, usage);
		close(con);
		return listCount;
	}

	public ArrayList<Book> getBookList(int page, int limit, String search, String value, String usage) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		ArrayList<Book> bookList = bookDAO.selectBookList(page, limit, search, value, usage);
		close(con);
		return bookList;
	}

}
