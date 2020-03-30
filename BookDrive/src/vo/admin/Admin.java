package vo.admin;

public class Admin {
	//관리자(통합, 도서관)의 정보를 저장/리스트로 불러오는 클래스
	//관리자 로그인, 관리자 리스트에서 사용
	private String adminNum; //인덱스번호
	private String libCode; //도서관코드
	private String libName; //도서관이름(도서관관리자 리스트 검색시 사용)
	private String adminId; //아이디
	private String adminPassword; //비밀번호
	private String adminName; //이름
	private String adminTel; //전화번호
	private String adminEmail; //이메일
	private String adminGrade; //등급(C:통합, L:도서관)
	private String adminApv; //승인유무(NO:승인안됨 YES:승인됨)
	public String getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
	}
	public String getLibCode() {
		return libCode;
	}
	public void setLibCode(String libCode) {
		this.libCode = libCode;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminTel() {
		return adminTel;
	}
	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminGrade() {
		return adminGrade;
	}
	public void setAdminGrade(String adminGrade) {
		this.adminGrade = adminGrade;
	}
	public String getAdminApv() {
		return adminApv;
	}
	public void setAdminApv(String adminApv) {
		this.adminApv = adminApv;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
}
