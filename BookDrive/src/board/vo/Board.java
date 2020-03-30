package board.vo;

public class Board {
	private int boardNum;
	private int memIndex;
	private String boardSubject;
	private String boardPass;
	private String boardContent;
	private int boardRCount;
	private String boardDate;
	private String boardWriter;
	private String boardFlag;
	private String boardFile;
	private String boardWriterFlag;
	private String boardReplyFlag;
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
	public int getBoardRCount() {
		return boardRCount;
	}
	public void setBoardRCount(int boardRCount) {
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
	public int getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}
	public int getBoardLev() {
		return boardLev;
	}
	public void setBoardLev(int boardLev) {
		this.boardLev = boardLev;
	}
	public int getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
	private int boardRef;
	private int boardLev;
	private int boardSeq;
	public String getBoardReplyFlag() {
		return boardReplyFlag;
	}
	public void setBoardReplyFlag(String boardReplyFlag) {
		this.boardReplyFlag = boardReplyFlag;
	}
	public int getMemIndex() {
		return memIndex;
	}
	public void setMemIndex(int memIndex) {
		this.memIndex = memIndex;
	}

}
