package book.dao.admin;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.sql.DataSource;

import vo.admin.Admin;
import vo.admin.Book;
import vo.admin.BookRental;
import vo.admin.BookRentalInfo;
import vo.admin.LibAdminApv;
import vo.admin.Member;

public class BookDAO {
	DataSource ds;
	Connection con;
	private static BookDAO bookDAO;
	
	private BookDAO() {}
	
	public static BookDAO getInstance() {
		if (bookDAO == null) {
			bookDAO = new BookDAO();
		}
		return bookDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int selectBookListCount(String libCode, String search, String keyword, String bookState) { //책 리스트의 갯수 구하기
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String query = "SELECT count(*)" + 
						" FROM bookInfo where ";
		query = !search.equals("") ? query + search : query + " bookName";
		query += " like '%" + keyword + "%'";
		query = !libCode.equals("") ? query + " and libCode = '" + libCode + "'" : query + "";
		query = !bookState.equals("") ? query + " and bookState = '" + bookState + "'" : query + "";
		System.out.println("페이징 카운트 쿼리 : " + query);
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("getBookListCount 에러 : " + ex);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		
		return listCount;
	}
	
	public ArrayList<Book> getBookList(int page, int limit, String libCode, String search, String keyword, String bookState) {
		//도서 리스트(통합관리자용)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Book> bookList = new ArrayList<>();
		Book book = null;
		int startrow = (page - 1) * limit; //읽기 시작할 row 번호(넘어온 페이지값에 limit를 곱한 값이 되어야 함)
		String query = "select * from (" + 
						"SELECT @rownum := @rownum + 1 AS rownum, a.*, b.libName" + 
						" FROM bookInfo AS a" + 
						" JOIN library AS b ON a.libCode = b.libCode, (SELECT @rownum:=0) tmp where a.";
		query = !search.equals("") ? query + search : query + "bookName";
		query += " like '%" + keyword + "%' and a.libCode like '%" + libCode + "%' and a.bookState like '%" + bookState + "%') as a";
		query += " where rownum >= " + (startrow + 1) + " and rownum <= " + (page * limit);
		System.out.println("Query : " + query);
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setRowNum(rs.getString("rownum"));
				book.setBookNum(rs.getString("bookNum"));
				book.setLibName(rs.getString("libName"));
				book.setBookName(rs.getString("bookName"));
//				if (!keyword.equals("")) {
//					book.setBookName(rs.getString("bookName").replace(keyword, "<span class='hilight'>"+keyword+"</span>"));
//				} else {
//					book.setBookName(rs.getString("bookName"));
//				}
				book.setBookWriter(rs.getString("bookWriter"));
				book.setBookPub(rs.getString("bookPub"));
				book.setBookPubDate(rs.getString("bookPubDate"));
				book.setISBN(rs.getString("ISBN"));
				book.setBookRegDate(rs.getString("bookRegDate"));
				book.setBookState(rs.getString("bookState"));
				book.setBookQty(rs.getString("bookQty"));
				book.setBookImage(rs.getString("bookImage"));
				book.setBookCategory(rs.getString("bookCategory"));
				bookList.add(book);
			}
		} catch (Exception e) {
			System.out.println("getBookList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return bookList;
	}

	public ArrayList<Hashtable<String, String>> getLibNameList() { //selectBox에 적용할 도서관 리스트를 가져옴
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Hashtable<String, String>> libNameList = new ArrayList<>();
		String query = "select libCode, libName from library";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Hashtable<String, String> table = new Hashtable<>();
				table.put("code", rs.getString("libCode"));
				table.put("name", rs.getString("libName"));
				libNameList.add(table);
			}
		} catch (Exception e) {
			System.out.println("getLibraryNameList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return libNameList;
	}

	public ArrayList<BookRentalInfo> getBookInfo(String bookNum) { //도서 상세정보(ajax 사용)
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookRentalInfo> bookRentalInfoList = new ArrayList<>();
		BookRentalInfo bookRentalInfo = null;
		String query = "SELECT a.renIdvNum, b.libName, c.memName, c.memId, a.renFlag, a.renBrwDate, a.renBrwInvDate, a.renRevDate, a.renRevInvDate, a.renReturnDate" + 
						" FROM rentalIdv AS a" + 
						" LEFT JOIN library AS b ON a.libCode = b.libCode" + 
						" LEFT JOIN member AS c ON a.memIndex = c.memIndex" +
						" where a.bookNum = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, bookNum);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookRentalInfo = new BookRentalInfo();
				bookRentalInfo.setRenIdvNum(rs.getInt("renIdvNum"));
				bookRentalInfo.setLibName(rs.getString("libName"));
				bookRentalInfo.setMemName(rs.getString("memName"));
				bookRentalInfo.setMemId(rs.getString("memId"));
				bookRentalInfo.setRenFlag(rs.getString("renFlag"));
				bookRentalInfo.setRenBrwDate(rs.getString("renBrwDate") != null ? rs.getString("renBrwDate") : "");
				bookRentalInfo.setRenBrwInvDate(rs.getString("renBrwInvDate") != null ? rs.getString("renBrwInvDate") : "");
				bookRentalInfo.setRenRevDate(rs.getString("renRevDate") != null ? rs.getString("renRevDate") : "");
				bookRentalInfo.setRenRevInvDate(rs.getString("renRevInvDate") != null ? rs.getString("renRevInvDate") : "");
				bookRentalInfo.setRenReturnDate(rs.getString("renReturnDate") != null ? rs.getString("renReturnDate") : "");
				bookRentalInfoList.add(bookRentalInfo);
			}
		} catch (Exception e) {
			System.out.println("getBookInfo 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return bookRentalInfoList;
	}

	public ArrayList<BookRental> getBookRevList() { //도서 예약내역
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookRental bookRental = null;
		ArrayList<BookRental> bookRevList = new ArrayList<>();
		String query = "SELECT * FROM view_member_info WHERE renFlag = 'rev'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookRental = new BookRental();
				bookRental.setBookName(rs.getString("bookName"));
				bookRental.setBookWriter(rs.getString("bookWriter"));
				bookRental.setLibName(rs.getString("libName"));
				bookRental.setLibCode(rs.getString("libCode"));
				bookRental.setISBN(rs.getString("ISBN"));
				bookRental.setMemId(rs.getString("memId"));
				bookRental.setMemName(rs.getString("memName"));
				bookRental.setRenIdvDelFlag(rs.getString("renIdvDelFlag"));
				bookRental.setRenDate(rs.getString("renRevDate"));
				bookRental.setRenIdvDate(rs.getString("renRevInvDate"));
				bookRevList.add(bookRental);
			}
		} catch (Exception e) {
			System.out.println("getBookRevList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return bookRevList;
	}

	public ArrayList<BookRental> getBookBrwList() { //도서 대출내역
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookRental bookRental = null;
		ArrayList<BookRental> bookBrwList = new ArrayList<>();
		String query = "SELECT * FROM view_member_info WHERE renFlag = 'brw'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookRental = new BookRental();
				bookRental.setBookName(rs.getString("bookName"));
				bookRental.setBookWriter(rs.getString("bookWriter"));
				bookRental.setLibName(rs.getString("libName"));
				bookRental.setLibCode(rs.getString("libCode"));
				bookRental.setISBN(rs.getString("ISBN"));
				bookRental.setMemId(rs.getString("memId"));
				bookRental.setMemName(rs.getString("memName"));
				bookRental.setRenIdvDelFlag(rs.getString("renIdvDelFlag"));
				bookRental.setRenDate(rs.getString("renBrwDate"));
				bookRental.setRenIdvDate(rs.getString("renBrwInvDate"));
				bookBrwList.add(bookRental);
			}
		} catch (Exception e) {
			System.out.println("getBookBrwList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return bookBrwList;
	}

	public ArrayList<String> getISBNArray() { //도서 ISBN가져오는 메소드
		// TODO Auto-generated method stub
//		String[] isbnArray = new String[1000];
		ArrayList<String> isbnList = new ArrayList<>();
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT bookNum, isbn FROM bookInfo WHERE bookNum >= 40001 AND bookNum <= 50000";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
//				System.out.println(rs.getString("bookNum") + " : " + rs.getString("isbn"));
//				isbnArray[count] = !(rs.getString("isbn").equals("")) ? rs.getString("isbn") : "";
//				isbnArray[count] = rs.getString("ISBN");
				if (rs.getString("ISBN").equals("") || rs.getString("ISBN") == null) {
					System.out.println("ISBN NULL");
				}
				isbnList.add(rs.getString("ISBN"));
				count++;
			}
		} catch (Exception e) {
			System.out.println("에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return isbnList;
	}

	public int insertImageURL(String isbn, String imageURL) { //도서 이미지URL을 입력하는 메소드
		// TODO Auto-generated method stub
//		System.out.println("isbn : " + isbn + ", imageURL : " + imageURL);
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String query = "update bookInfo set bookImage = ? where isbn = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, imageURL);
			pstmt.setString(2, isbn);
			insertCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertImageURL 에러 :::: " + e);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		return insertCount;
	}

	public int updateBookState(String bookNum, String updateState) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		String query = "update bookInfo set bookState = ? where bookNum = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, updateState);
			pstmt.setString(2, bookNum);
			updateCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("updateBookState 에러 :::: " + e);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		return updateCount;
	}

	public ArrayList<BookRental> getBookOutBrwList() { //도서 관외대출 리스트
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookRental bookRental = null;
		ArrayList<BookRental> bookOutBrwList = new ArrayList<>();
		String query = "SELECT * FROM view_member_info WHERE renFlag = 'outbrw'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookRental = new BookRental();
				bookRental.setBookName(rs.getString("bookName"));
				bookRental.setBookWriter(rs.getString("bookWriter"));
				bookRental.setLibName(rs.getString("libName"));
				bookRental.setLibCode(rs.getString("libCode"));
				bookRental.setISBN(rs.getString("ISBN"));
				bookRental.setMemId(rs.getString("memId"));
				bookRental.setMemName(rs.getString("memName"));
				bookRental.setRenIdvDelFlag(rs.getString("renIdvDelFlag"));
				bookRental.setRenDate(rs.getString("renBrwDate"));
				bookRental.setRenIdvDate(rs.getString("renBrwInvDate"));
				bookOutBrwList.add(bookRental);
			}
		} catch (Exception e) {
			System.out.println("getBookOutBrwList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return bookOutBrwList;
	}
	
	public ArrayList<BookRental> getBookOutRevList() { //도서 관외예약 리스트
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookRental bookRental = null;
		ArrayList<BookRental> bookOutRevList = new ArrayList<>();
		String query = "SELECT * FROM view_member_info WHERE renFlag = 'outrev'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bookRental = new BookRental();
				bookRental.setBookName(rs.getString("bookName"));
				bookRental.setBookWriter(rs.getString("bookWriter"));
				bookRental.setLibName(rs.getString("libName"));
				bookRental.setLibCode(rs.getString("libCode"));
				bookRental.setISBN(rs.getString("ISBN"));
				bookRental.setMemId(rs.getString("memId"));
				bookRental.setMemName(rs.getString("memName"));
				bookRental.setRenIdvDelFlag(rs.getString("renIdvDelFlag"));
				bookRental.setRenDate(rs.getString("renBrwDate"));
				bookRental.setRenIdvDate(rs.getString("renBrwInvDate"));
				bookOutRevList.add(bookRental);
			}
		} catch (Exception e) {
			System.out.println("getBookOutRevList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(pstmt);
		}
		return bookOutRevList;
	}
}