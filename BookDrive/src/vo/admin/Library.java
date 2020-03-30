package vo.admin;

public class Library {
	//도서관의 정보를 저장/리스트로 불러오는 클래스
	private String libCode;
	private String libName;
	private String libZip;
	private String libAddr1;
	private String libAddr2;
	private String libTel;
	private String libFax;
	private String libLa;
	private String libLo;
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
	public String getLibZip() {
		return libZip;
	}
	public void setLibZip(String libZip) {
		this.libZip = libZip;
	}
	public String getLibAddr1() {
		return libAddr1;
	}
	public void setLibAddr1(String libAddr1) {
		this.libAddr1 = libAddr1;
	}
	public String getLibAddr2() {
		return libAddr2;
	}
	public void setLibAddr2(String libAddr2) {
		this.libAddr2 = libAddr2;
	}
	public String getLibTel() {
		return libTel;
	}
	public void setLibTel(String libTel) {
		this.libTel = libTel;
	}
	public String getLibHomePage() {
		return libHomePage;
	}
	public void setLibHomePage(String libHomePage) {
		this.libHomePage = libHomePage;
	}
	String libHomePage;
	public String getLibFax() {
		return libFax;
	}
	public void setLibFax(String libFax) {
		this.libFax = libFax;
	}
	public String getLibLa() {
		return libLa;
	}
	public void setLibLa(String libLa) {
		this.libLa = libLa;
	}
	public String getLibLo() {
		return libLo;
	}
	public void setLibLo(String libLo) {
		this.libLo = libLo;
	}
}
