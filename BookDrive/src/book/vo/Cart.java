package book.vo;

public class Cart {
	private String cartNum;
	private String bookNum;
	private String libCode;
	private String memIndex;
	private String cartDelFlag;
	private String bookName;
	private String bookPub;
	private String bookPubDate;
	private String bookWriter;
	private String ISBN;
	
	
	public String getCartNum() {
		return cartNum;
	}
	public void setCartNum(String cartNum) {
		this.cartNum = cartNum;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookPub() {
		return bookPub;
	}
	public void setBookPub(String bookPub) {
		this.bookPub = bookPub;
	}
	public String getBookPubDate() {
		return bookPubDate;
	}
	public void setBookPubDate(String bookPubDate) {
		this.bookPubDate = bookPubDate;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
	}
	public String getMemIndex() {
		return memIndex;
	}
	public void setMemIndex(String memIndex) {
		this.memIndex = memIndex;
	}
	public String getCartDelFlag() {
		return cartDelFlag;
	}
	public void setCartDelFlag(String cartDelFlag) {
		this.cartDelFlag = cartDelFlag;
	}
	
	public String getLibCode() {
		return libCode;
	}
	public void setLibCode(String libCode) {
		this.libCode = libCode;
	}
	
}
