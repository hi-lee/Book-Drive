package vo.admin;

import java.util.ArrayList;
import java.util.List;

public class Member {
	private String memIndex; //회원번호
	private String memName; //회원이름
	private String memBirth; //회원생년월일
	private String memZip; //회원우편번호
	private String memTel; //회원전화번호
	private String memCarNum; //회원차번호
	private String memGrade; //회원등급
	private String memId; //회원아이디
	private String memPass; //회원비밀번호
	private String memEmail; //회원이메일
	private String memAddr1; //회원상세주소1
	private String memAddr2; //회원상세주소2
	private ArrayList<String> memRenFlag; //회원대여종류(rev=예약, brw=대출, overdue=관외예약)
	private ArrayList<String> bookName; //회원대여책이름
	private ArrayList<String> libName; //회원대여책소속도서관
	private ArrayList<String> renRevDate; //회원예약일자
	private ArrayList<String> renRevInvDate; //회원예약종료일자
	private ArrayList<String> renBrwDate; //회원대출일자
	private ArrayList<String> renBrwInvDate; //회원대출종료일자
	private ArrayList<String> renIdvDelFlag; //회원대여삭제유무(완료유무)
	public Member() { //멤버 객체 선언시 arraylist 객체 생성
		this.memRenFlag = new ArrayList<>();
		this.bookName = new ArrayList<>();
		this.libName = new ArrayList<>();
		this.renRevDate = new ArrayList<>();
		this.renRevInvDate = new ArrayList<>();
		this.renBrwDate = new ArrayList<>();
		this.renBrwInvDate = new ArrayList<>();
		this.renIdvDelFlag = new ArrayList<>();
	}
	private String memRevCount; //회원예약카운트
	private String memBrwCount; //회원대출카운트
	private String memOverdueCount; //회원관외예약카운트
	public String getMemIndex() {
		return memIndex;
	}
	public void setMemIndex(String memIndex) {
		this.memIndex = memIndex;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}
	public String getMemZip() {
		return memZip;
	}
	public void setMemZip(String memZip) {
		this.memZip = memZip;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemCarNum() {
		return memCarNum;
	}
	public void setMemCarNum(String memCarNum) {
		this.memCarNum = memCarNum;
	}
	public String getMemGrade() {
		return memGrade;
	}
	public void setMemGrade(String memGrade) {
		this.memGrade = memGrade;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemAddr1() {
		return memAddr1;
	}
	public void setMemAddr1(String memAddr1) {
		this.memAddr1 = memAddr1;
	}
	public String getMemAddr2() {
		return memAddr2;
	}
	public void setMemAddr2(String memAddr2) {
		this.memAddr2 = memAddr2;
	}
	public ArrayList<String> getMemRenFlag() {
		return memRenFlag;
	}
	public void setMemRenFlag(String memRenFlag) {
		this.memRenFlag.add(memRenFlag);
	}
	public ArrayList<String> getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName.add(bookName);
	}
	public ArrayList<String> getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName.add(libName);
	}
	public String getMemRevCount() {
		return memRevCount;
	}
	public void setMemRevCount(String memRevCount) {
		this.memRevCount = memRevCount;
	}
	public String getMemBrwCount() {
		return memBrwCount;
	}
	public void setMemBrwCount(String memBrwCount) {
		this.memBrwCount = memBrwCount;
	}
	public String getMemOverdueCount() {
		return memOverdueCount;
	}
	public void setMemOverdueCount(String memOverdueCount) {
		this.memOverdueCount = memOverdueCount;
	}
	
	
	public ArrayList<String> getRenRevDate() {
		return renRevDate;
	}
	public void setRenRevDate(String renRevDate) {
		this.renRevDate.add(renRevDate);
	}
	public ArrayList<String> getRenRevInvDate() {
		return renRevInvDate;
	}
	public void setRenRevInvDate(String renRevInvDate) {
		this.renRevInvDate.add(renRevInvDate);
	}
	public ArrayList<String> getRenBrwDate() {
		return renBrwDate;
	}
	public void setRenBrwDate(String renBrwDate) {
		this.renBrwDate.add(renBrwDate);
	}
	public ArrayList<String> getRenBrwInvDate() {
		return renBrwInvDate;
	}
	public void setRenBrwInvDate(String renBrwInvDate) {
		this.renBrwInvDate.add(renBrwInvDate);
	}
	public ArrayList<String> getRenIdvDelFlag() {
		return renIdvDelFlag;
	}
	public void setRenIdvDelFlag(String renIdvDelFlag) {
		this.renIdvDelFlag.add(renIdvDelFlag);
	}
	
}
