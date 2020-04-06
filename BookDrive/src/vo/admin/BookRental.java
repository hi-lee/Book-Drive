package vo.admin;

public class BookRental {
	private String bookName;
	private String bookWriter;
	private String ISBN;
	private String libName;
	private String libCode;
	private String memId;
	private String memName;
	private String renDate;
	private String renIdvDate;
	private String renIdvDelFlag;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookWriter() {
		return bookWriter;
	}
	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getRenDate() {
		return renDate;
	}
	public void setRenDate(String renDate) {
		this.renDate = renDate;
	}
	public String getRenIdvDate() {
		return renIdvDate;
	}
	public void setRenIdvDate(String renIdvDate) {
		this.renIdvDate = renIdvDate;
	}
	public String getRenIdvDelFlag() {
		return renIdvDelFlag;
	}
	public void setRenIdvDelFlag(String renIdvDelFlag) {
		this.renIdvDelFlag = renIdvDelFlag;
	}
	public String getLibCode() {
		return libCode;
	}
	public void setLibCode(String libCode) {
		this.libCode = libCode;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
}
