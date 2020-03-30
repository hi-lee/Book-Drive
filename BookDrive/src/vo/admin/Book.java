package vo.admin;

import java.util.ArrayList;

public class Book {
	private String rowNum; //검색결과번호
	private String libCode; //도서관코드
	private String bookNum; //도서번호
	private String libName; //도서관이름(리스트용)
	private String bookName; //도서이름
	private String bookWriter; //도서저자
	private String bookPub; //도서출판사
	private String bookPubDate; //도서출판일
	private String ISBN; //도서ISBN
	private String bookRegDate; //도서등록일
	private String bookState; //도서상태
	private String bookQty; //도서권수
	private String bookImage; //도서이미지경로
	private String bookCategory; //도서카테고리
	private ArrayList<String> libCodeList; //도서관코드(검색용)
	private ArrayList<String> libNameList; //도서관이름(검색용)
	
	public Book() {
		libCodeList = new ArrayList<>();
		libNameList = new ArrayList<>();
	}
	public String getBookNum() {
		return bookNum;
	}
	public void setBookNum(String bookNum) {
		this.bookNum = bookNum;
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
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}
	public String getBookRegDate() {
		return bookRegDate;
	}
	public void setBookRegDate(String bookRegDate) {
		this.bookRegDate = bookRegDate;
	}
	public String getBookState() {
		return bookState;
	}
	public void setBookState(String bookState) {
		this.bookState = bookState;
	}
	public String getBookQty() {
		return bookQty;
	}
	public void setBookQty(String bookQty) {
		this.bookQty = bookQty;
	}
	public String getBookImage() {
		return bookImage;
	}
	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	public String getBookCategory() {
		return bookCategory;
	}
	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
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
	public ArrayList<String> getLibCodeList() {
		return libCodeList;
	}
	public void setLibCodeList(String libCodeList) {
		this.libCodeList.add(libCodeList);
	}
	public ArrayList<String> getLibNameList() {
		return libNameList;
	}
	public void setLibNameList(String libNameList) {
		this.libNameList.add(libNameList);
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getLibCode() {
		return libCode;
	}
	public void setLibCode(String libCode) {
		this.libCode = libCode;
	}
}