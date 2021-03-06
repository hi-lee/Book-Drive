package book.svc.lib;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

import book.dao.lib.BookDAO;
import vo.admin.Book;

public class BookListService {
	public int getListCount(String flag, String keyword, String libCode, String bookState) throws Exception { //페이징에 사용
		int listCount = 0;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		listCount = bookDAO.selectBookListCount(flag, keyword, libCode, bookState); //전체 글 갯수를 가져옴
		if (con != null) close(con);
		return listCount;
	}
	
	public ArrayList<Book> selectBookList(int page, int limit, String flag, String keyword, String libCode, String bookState) {
		// TODO Auto-generated method stub
		ArrayList<Book> bookList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		bookList = bookDAO.getBookList(page, limit, flag, keyword, libCode, bookState);
		if (con != null) close(con);
		return bookList;
	}

	public ArrayList<Hashtable<String, String>> selectLibNameList() {
		// TODO Auto-generated method stub
		ArrayList<Hashtable<String, String>> libNameList = null;
		Connection con = getConnection();
		BookDAO bookDAO = BookDAO.getInstance();
		bookDAO.setConnection(con);
		libNameList = bookDAO.getLibNameList();
		if (con != null) close(con);
		return libNameList;
	}

}
