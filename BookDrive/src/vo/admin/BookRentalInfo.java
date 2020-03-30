package vo.admin;

public class BookRentalInfo {
	//도서 상세정보 조회시 도서의 대여정보를 저장하는 클래스(BookInfo.bookA)
	private int renIdvNum; //대여번호
	private String libName; //도서관이름
	private String memName; //대여자이름
	private String memId; //대여자아이디
	private String renFlag; //대여종류
	private String renBrwDate; //대출일자
	private String renBrwInvDate; //대출만료일자
	private String renRevDate; //예약일자
	private String renRevInvDate; //예약만료일자
	private String renReturnDate; //반납일자
	public int getRenIdvNum() {
		return renIdvNum;
	}
	public void setRenIdvNum(int renIdvNum) {
		this.renIdvNum = renIdvNum;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getRenFlag() {
		return renFlag;
	}
	public void setRenFlag(String renFlag) {
		this.renFlag = renFlag;
	}
	public String getRenBrwDate() {
		return renBrwDate;
	}
	public void setRenBrwDate(String renBrwDate) {
		this.renBrwDate = renBrwDate;
	}
	public String getRenBrwInvDate() {
		return renBrwInvDate;
	}
	public void setRenBrwInvDate(String renBrwInvDate) {
		this.renBrwInvDate = renBrwInvDate;
	}
	public String getRenRevDate() {
		return renRevDate;
	}
	public void setRenRevDate(String renRevDate) {
		this.renRevDate = renRevDate;
	}
	public String getRenRevInvDate() {
		return renRevInvDate;
	}
	public void setRenRevInvDate(String renRevInvDate) {
		this.renRevInvDate = renRevInvDate;
	}
	public String getRenReturnDate() {
		return renReturnDate;
	}
	public void setRenReturnDate(String renReturnDate) {
		this.renReturnDate = renReturnDate;
	}
}
