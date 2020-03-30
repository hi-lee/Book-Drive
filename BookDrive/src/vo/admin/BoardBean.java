package vo.admin;

import java.sql.Date;

public class BoardBean {
	private int rowNum; //검색결과번호
	private int boardNum; //글번호
	private int memIndex; //회원번호(일반회원 글작성시 사용)
	private int adminNum; //관리자번호(관리자 글작성시 사용)
	private String boardSubject; //글제목
	private String boardPass; //글비밀번호
	private String boardContent; //글내용
	private String boardRCount; //글조회수
	private String boardDate; //글작성일자
	private String boardWriter; //글작성자
	private String boardFlag; //글 게시판구분(공지/QnA/희망도서 등등)
	private String boardFile; //글첨부파일
	private String boardWriterFlag; //글 작성자구분(관리자, 사용자 등등)
	private String boardReplyFlag; //답글유무구분
	private String boardRef;
	private String boardLev;
	private String boardSeq;
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardSubject() {
		return boardSubject;
	}
	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}
	public String getBoardPass() {
		return boardPass;
	}
	public void setBoardPass(String boardPass) {
		this.boardPass = boardPass;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardRCount() {
		return boardRCount;
	}
	public void setBoardRCount(String boardRCount) {
		this.boardRCount = boardRCount;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public String getBoardFlag() {
		return boardFlag;
	}
	public void setBoardFlag(String boardFlag) {
		this.boardFlag = boardFlag;
	}
	public String getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}
	public String getBoardWriterFlag() {
		return boardWriterFlag;
	}
	public void setBoardWriterFlag(String boardWriterFlag) {
		this.boardWriterFlag = boardWriterFlag;
	}
	public String getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(String boardRef) {
		this.boardRef = boardRef;
	}
	public String getBoardLev() {
		return boardLev;
	}
	public void setBoardLev(String boardLev) {
		this.boardLev = boardLev;
	}
	public String getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(String boardSeq) {
		this.boardSeq = boardSeq;
	}
	public int getMemIndex() {
		return memIndex;
	}
	public void setMemIndex(int memIndex) {
		this.memIndex = memIndex;
	}
	public int getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(int adminNum) {
		this.adminNum = adminNum;
	}
	@Override
	public String toString() {
		return "BoardBean [boardNum=" + boardNum + ", memIndex=" + memIndex + ", adminNum=" + adminNum
				+ ", boardSubject=" + boardSubject + ", boardPass=" + boardPass + ", boardContent=" + boardContent
				+ ", boardRCount=" + boardRCount + ", boardDate=" + boardDate + ", boardWriter=" + boardWriter
				+ ", boardFlag=" + boardFlag + ", boardFile=" + boardFile + ", boardWriterFlag=" + boardWriterFlag
				+ ", boardRef=" + boardRef + ", boardLev=" + boardLev + ", boardSeq=" + boardSeq + "]";
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public String getBoardReplyFlag() {
		return boardReplyFlag;
	}
	public void setBoardReplyFlag(String boardReplyFlag) {
		this.boardReplyFlag = boardReplyFlag;
	}
}
