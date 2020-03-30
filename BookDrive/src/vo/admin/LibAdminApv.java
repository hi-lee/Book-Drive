package vo.admin;

public class LibAdminApv {
	//도서관 관리자 가입신청 인원의 정보를 저장/리스트로 불러오는 클래스
	private String libCode; //도서관코드
	private String libName; //도서관이름
	private String libTel; //도서관전화번호
	private String adminNum; //관리자인덱스번호
	private String adminName; //관리자이름
	private String adminId; //관리자아이디
	private String adminTel; //관리자전화번호
	public String getLibCode() {
		return libCode;
	}
	public void setLibCode(String libCode) {
		this.libCode = libCode;
	}
	public String getLibName() {
		return libName;
	}
	public void setLibName(String libName) {
		this.libName = libName;
	}
	public String getLibTel() {
		return libTel;
	}
	public void setLibTel(String libTel) {
		this.libTel = libTel;
	}
	public String getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(String adminNum) {
		this.adminNum = adminNum;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminTel() {
		return adminTel;
	}
	public void setAdminTel(String adminTel) {
		this.adminTel = adminTel;
	}
}
