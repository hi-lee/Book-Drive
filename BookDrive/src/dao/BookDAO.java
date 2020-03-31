package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import book.vo.Book;
import book.vo.Cart;
import book.vo.RentalIdv;

public class BookDAO {
	Connection con;
	private static BookDAO bookDAO;

	public static BookDAO getInstance() {
		// TODO Auto-generated method stub
		if (bookDAO == null)
			bookDAO = new BookDAO();
		return bookDAO;
	}

	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;
	}

	public int selectCount(String search, String value, String usage) {
		// TODO Auto-generated method stub
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from bookInfo where " + search + " like ?";
		if (usage != null) {
			sql += "AND bookState = 3"; // 관외 bookState=3 조회시 쿼리문 조절해야함
		}
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + value + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				listCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("bookList err  :   " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}

	public ArrayList<Book> selectBookList(int page, int limit, String search, String value, String usage) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String book_list_sql = "select * from bookInfo where ";
		ArrayList<Book> bookList = new ArrayList<Book>();
		Book book = null;
		int startrow = (page - 1) * limit;
		if (usage != null) {
			book_list_sql += "bookState = 3 AND ";
		}
		if (search.equals("bookName")) {
			book_list_sql += "bookName like ? ";
		} else if (search.equals("bookWriter")) {
			book_list_sql += "bookWriter like ? ";
		} else if (search.equals("bookPub")) {
			book_list_sql += "bookPub like ? ";
		} else if (search.equals("ISBN")) {
			book_list_sql += "ISBN like ? ";
		}
		book_list_sql += "order by bookRegDate asc limit ?,?;";

		try {
			pstmt = con.prepareStatement(book_list_sql);
			pstmt.setString(1, "%" + value + "%");
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setBookNum(rs.getString("bookNum"));
				book.setLibCode(rs.getString("libCode"));
				book.setBookName(rs.getString("bookName"));
				book.setBookWriter(rs.getString("bookWriter"));
				book.setBookPub(rs.getString("bookPub"));
				book.setBookPubDate(rs.getString("bookPubDate"));
				book.setISBN(rs.getString("ISBN"));
				book.setBookRegDate(rs.getString("bookRegDate"));
				book.setBookState(rs.getString("bookState"));
				book.setBookImage(rs.getString("bookImage"));
				bookList.add(book);
			}
		} catch (SQLException e) {
			System.out.println("getBookList err   :   " + e);
		} finally {
			close(rs);
			close(pstmt);
		}

		return bookList;
	}

	public Book selectBook(String bookNum) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Book book = null;
		String select_book_sql = "select * from bookInfo where bookNum = ?";
		try {
			pstmt = con.prepareStatement(select_book_sql);
			pstmt.setString(1, bookNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				book = new Book();
				book.setBookNum(rs.getString("bookNum"));
				book.setLibCode(rs.getString("libCode"));
				book.setBookName(rs.getString("bookName"));
				book.setBookWriter(rs.getString("bookWriter"));
				book.setBookPub(rs.getString("bookPub"));
				book.setISBN(rs.getString("ISBN"));
				book.setBookImage(rs.getString("bookImage"));
				book.setBookPubDate(rs.getString("bookPubDate"));
				book.setBookRegDate(rs.getString("bookRegDate"));
				book.setBookState(rs.getString("bookState"));
			}
		} catch (SQLException e) {
			System.out.println("selectBook err  :  " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return book;
	}

	public RentalIdv getRentalIdv(String bookNum) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RentalIdv rentalIdv = null;
		try {
			pstmt = con.prepareStatement(
					"select * from rentalIdv where bookNum = ? and renFlag = 'brw' and renIdvDelFlag = 0");
			pstmt.setString(1, bookNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rentalIdv = new RentalIdv();
				rentalIdv.setMemIndex(rs.getInt("memIndex"));
				rentalIdv.setBookNum(rs.getInt("bookNum"));
				rentalIdv.setRenFlag(rs.getString("renFlag"));
				rentalIdv.setRenRevDate(rs.getString("renRevDate"));
				rentalIdv.setRenRevInvDate(rs.getString("renRevInvDate"));
				rentalIdv.setRenBrwDate(rs.getString("renBrwDate"));
				rentalIdv.setRenBrwInvDate(rs.getString("renBrwInvDate"));
				rentalIdv.setRenOutRev(rs.getString("renOutRev"));
				rentalIdv.setRenIdvDelFlag(rs.getString("renIdvDelFlag"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("getRental Err : " + e);
		} finally {
			close(pstmt);
			close(rs);
		}
		return rentalIdv;
	}

	public int insertState(String memIndex, String bookNum, String libCode, String state, String usage) {
		// TODO Auto-generated method stub
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String state_sql = "update bookInfo set bookState = ? where bookNum = ?";
		String rentalIdv_sql = "";
		try {
			pstmt = con.prepareStatement(state_sql);
//				state:0 대출가능, 1:대출중, 2:예약중  3:관외보관 4:관외대출 5:관외예약 6:대출+예약 7:대출+관외예약 8:관외대출+관외예약 
			if (usage == null) { // 관외예약이 아닌경우
				if (state.equals("0") || state.equals("2")) { // 대출가능,예약중> 대출> 대출중
					pstmt.setString(1, "1");
				} else if (state.equals("1")) { // 대출중> 예약> 대출+예약중
					pstmt.setString(1, "6");
				}
//				else if (state.equals("3")) { // 관외보관> 대출> 관외대출
//					pstmt.setString(1, "4");
//				}
			} else {
				if (state.equals("0")) {
					pstmt.setString(1, "1");
				} else if (state.equals("1")) { // 대출중+관외예약
					pstmt.setString(1, "7");
				} else if (state.equals("4")) { // 관외대출+관외예약
					pstmt.setString(1, "8");
				} else if (state.equals("5")) { // 반납+관외예약
					pstmt.setString(1, "4");
				} else if (state.equals("3")) {
					pstmt.setString(1, "4");
				}
			}

			pstmt.setString(2, bookNum);
			int updateCount = pstmt.executeUpdate();

			System.out.println("insertState : " + state_sql + ", state : " + state);

			if (updateCount > 0) {
				rentalIdv_sql = "insert into rentalIdv (renIdvNum, memIndex, bookNum, libCode, renIdvDelFlag, renFlag, ";
				if (usage == null) {
					if (state.equals("0") || state.equals("2")) {
//						state:0,3 인 상태에서 쿼리 실행시 'brw', 대출날짜, 대출만료일 / state:1 'rev', 예약날짜, 예약 만료일 
						rentalIdv_sql += " renBrwDate, renBrwInvDate) ";
					} else if (state.equals("1")) {
						rentalIdv_sql += " renRevDate, renRevInvDate) ";
					}
				} else { // 0:대출가능 > 관외예약(isOut!=null)
					if (state.equals("0") || state.equals("1") || state.equals("4")) {
						rentalIdv_sql += " renRevDate, renRevInvDate) ";
					} else if (state.equals("3") || state.equals("5")) {
						rentalIdv_sql += " renBrwDate, renBrwInvDate) ";
					}
				}

				rentalIdv_sql += " values (0, ?, ?, ?, 0, ";

				if (usage == null) {
					if (state.equals("0") || state.equals("2")) { // 대출가능(0)일때 renFlag: 'brw', renBrwDate: now(),
						// renBrwInvDate: now + 14days
						rentalIdv_sql += " 'brw', now(), (now()+INTERVAL 14 DAY))";
					} else if (state.equals("1")) {
						rentalIdv_sql += " 'rev', now(), (now()+INTERVAL 1 DAY))";
					}
				} else {
					if (state.equals("3") || state.equals("5")) {
						rentalIdv_sql += " 'outbrw', now(), (now()+INTERVAL 14 DAY))";
					} else if (state.equals("0") || state.equals("1") || state.equals("4")) {
						rentalIdv_sql += " 'outrev', now(), (now()+INTERVAL 1 DAY))";
					}
				}

				System.out.println("insert SQL  : " + rentalIdv_sql);
				pstmt = con.prepareStatement(rentalIdv_sql);
				pstmt.setString(1, memIndex);
				pstmt.setString(2, bookNum);
				pstmt.setString(3, libCode);
				insertCount = pstmt.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("insertState err :  " + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	public int returnState(String bookNum, String usage, String state, String renNum) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String return_sql = "";
		ResultSet isRev = null;

		if (usage != null) {
			String chkRev = "select * from rentalIdv where bookNum = ? and renFlag = 'outrev' and renIdvDelFlag = 0";
			try {
				pstmt = con.prepareStatement(chkRev);
				pstmt.setString(1, bookNum);
				isRev = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("chkRev Err : " + e);
			}
		}

		if (isRev != null) { // 관외 + 관외예약중인 데이터가 있는지 조회
			return_sql = "update bookInfo set bookState = ";
			if (state.equals("5")) { // 없으면 관외보관으로 변경
				return_sql += "4 where bookNum = ?";
			} else { // 관외예약중이 있으면 책상태를 5(관외예약중)으로 변경
				return_sql += "3 where bookNum = ?";
			}

		} else { // usage==null(관내)일때
			return_sql = "update bookInfo set bookState = ";
			if (state.equals("6")) { // 6:대출+예약 > 반납 > 2:예약중
				return_sql += "2 where bookNum = ?";
			} else if (state.equals("2")) { // 2:예약중 > 대출 > 1:대출중
				return_sql += "1 where bookNum = ?";
			} else if (state.equals("7") || state.equals("8")) { // 7:대출+관외예약, 8:관외대출+관외예약
				return_sql += "5 where bookNum = ?"; // 5:관외예약중(예약자만 대출가능)
			} else if (state.equals("5")) { // 반납+관외예약
				return_sql += "4 where bookNum = ?";
			} else {
				return_sql += "0 where bookNum = ?";
			}
		}
		System.out.println("return : " + return_sql + "\n state : " + state + ", bookNum : " + bookNum);

		try {
			pstmt = con.prepareStatement(return_sql);
			pstmt.setString(1, bookNum);

			int updateRental = pstmt.executeUpdate();

			if (updateRental > 0) {
				String update_sql = "update rentalIdv set renReturnDate = now(), renIdvDelFlag = 1 where ";

				if (usage != null) {
					if (state.equals("5")) {
						update_sql += "bookNum = ? and renFlag = 'outrev'";
					} else {
						update_sql += "bookNum = ? and renFlag = 'outbrw'";
					}

					pstmt = con.prepareStatement(update_sql);
					pstmt.setString(1, bookNum);
				} else {
					if (state.equals("1") || state.equals("2") || state.equals("6") || state.equals("7")) { // 대출+예약
						update_sql += "renIdvNum = ? ";
						pstmt = con.prepareStatement(update_sql);
						pstmt.setString(1, renNum);
					}
				}
				updateCount = pstmt.executeUpdate();

				System.out.println("update rentalIdv : " + update_sql + "\n, bookNum : " + bookNum + ", renNum : "
						+ renNum + ", state : " + state);
			}
		} catch (SQLException e) {
			System.out.println("return err :  " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	public ArrayList<RentalIdv> getRentalList(String memIndex) {
		// TODO Auto-generated method stub
		ArrayList<RentalIdv> rentalList = new ArrayList<RentalIdv>();
		RentalIdv rentalIdv = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String rental_sql = "select * from rentalIdv left join bookInfo on rentalIdv.bookNum = bookInfo.bookNum where memIndex = ? order by renBrwDate desc, renRevDate desc";
		try {
			pstmt = con.prepareStatement(rental_sql);
			pstmt.setString(1, memIndex);
			rs = pstmt.executeQuery();
//			System.out.println(rental_sql);
			while (rs.next()) {
				rentalIdv = new RentalIdv();
				rentalIdv.setRenIdvNum(rs.getInt("renIdvNum"));
				rentalIdv.setMemIndex(rs.getInt("memIndex"));
				rentalIdv.setBookNum(rs.getInt("bookNum"));
				rentalIdv.setBookName(rs.getString("bookName"));
				rentalIdv.setLibCode(rs.getString("libCode"));
				rentalIdv.setRenFlag(rs.getString("renFlag"));
				rentalIdv.setRenBrwDate(rs.getString("renBrwDate"));
				rentalIdv.setRenBrwInvDate(rs.getString("renBrwInvDate"));
				rentalIdv.setRenRevDate(rs.getString("renRevDate"));
				rentalIdv.setRenRevInvDate(rs.getString("renRevInvDate"));
				rentalIdv.setRenReturnDate(rs.getString("renReturnDate"));
				rentalIdv.setRenIdvDelFlag(rs.getString("renIdvDelFlag"));
				rentalIdv.setBookState(rs.getString("bookState"));
				rentalList.add(rentalIdv);
			}
		} catch (SQLException e) {
			System.out.println("getRentalList err : " + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return rentalList;
	}

	public int revCancelState(int memIndex, String bookNum, String state) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		int updateBookInfo = 0;
		PreparedStatement pstmt = null;
		String cancel_sql = "update bookInfo set bookState = 1 where bookNum = ?";

		try {
			pstmt = con.prepareStatement(cancel_sql);
			pstmt.setString(1, bookNum);
			updateBookInfo = pstmt.executeUpdate();

			if (updateBookInfo > 0) {
				String update_sql = "update rentalIdv set renIdvDelFlag = 1 where bookNum = ? and renFlag = ";
				if (state.equals("5") || state.equals("7") || state.equals("8")) {
					update_sql += "'outrev'";
				} else {
					update_sql += "'rev'";
				}
				pstmt = con.prepareStatement(update_sql);
				pstmt.setString(1, bookNum);
				updateCount = pstmt.executeUpdate();

				System.out.println("update sql : " + update_sql + "\n, bookNum : " + bookNum + ", state : " + state);
			}
		} catch (SQLException e) {
			System.out.println("revCancel err  : " + e);
		} finally {
			close(pstmt);
		}
		return updateCount;
	}

	public int addCart(String memIndex, Book bookCart) {
		PreparedStatement pstmt = null;
		int insertCount = 0;
		String addCart_sql = "insert into cart values(0, ?, ?, ?, 0)";
		System.out.println(bookCart.getBookNum());
		try {
			pstmt = con.prepareStatement(addCart_sql);
			pstmt.setString(1, bookCart.getBookNum());
			pstmt.setString(2, bookCart.getLibCode());
			pstmt.setString(3, memIndex);
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("addCart Err  :::  " + e);
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	public ArrayList<Cart> selectCartList(String memIndex) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		Cart cart = null;
		try {
			pstmt = con.prepareStatement(
					"select * from cart left join bookInfo on cart.bookNum = bookInfo.bookNum where memIndex = ?");
			pstmt.setString(1, memIndex);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				cart = new Cart();
				cart.setCartNum(rs.getString("cartNum"));
				cart.setBookNum(rs.getString("bookNum"));
				cart.setMemIndex(rs.getString("memIndex"));
				cart.setLibCode(rs.getString("libCode"));
				cart.setCartDelFlag(rs.getString("cartDelFlag"));
				cart.setBookName(rs.getString("bookName"));
				cart.setBookWriter(rs.getString("bookWriter"));
				cart.setISBN(rs.getString("ISBN"));
				cart.setBookPub(rs.getString("bookPub"));
				cart.setBookPubDate(rs.getString("bookPubDate"));
				cartList.add(cart);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("selectCartList err :::  " + e);
		} finally {
			close(pstmt);
			close(rs);
		}
		return cartList;
	}

	public int delCartList(String memIndex, String[] cartNum) {
		// TODO Auto-generated method stub
		int delCount = 0;
		PreparedStatement pstmt = null;
		String delCart_sql = "delete from cart where memIndex = ? and cartNum = ? ";

		try {
			pstmt = con.prepareStatement(delCart_sql);
			pstmt.setString(1, memIndex);
			if (cartNum != null) {
				for (int i = 0; i < cartNum.length; i++) {
					pstmt.setString(2, cartNum[i]);
					delCount = pstmt.executeUpdate();
				}
			}
			System.out.println("delCart_sql:  " + delCart_sql);
		} catch (SQLException e) {
			System.out.println("delCart Err ::: " + e);
		}
		return delCount;
	}
}
