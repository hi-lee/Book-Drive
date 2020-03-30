package member.dao.admin;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.sql.DataSource;

import vo.admin.Admin;
import vo.admin.LibAdminApv;
import vo.admin.Member;

public class MemberDAO {
	DataSource ds;
	Connection con;
	private static MemberDAO memberDAO;
	
	private MemberDAO() {}
	
	public static MemberDAO getInstance() {
		if (memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}

	public ArrayList<Member> getMemberList() {
		//전체 회원 리스트(통합관리자용)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Member> memberList = new ArrayList<Member>();
		Member member = null;
		String query = "select * from member";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				member = new Member();
				member.setMemIndex(rs.getString("memIndex"));
				member.setMemName(rs.getString("memName"));
				member.setMemBirth(rs.getString("memBirth"));
				member.setMemZip(rs.getString("memZip"));
				member.setMemTel(rs.getString("memTel"));
				member.setMemCarNum(rs.getString("memCarNum"));
				member.setMemGrade(rs.getString("memGrade"));
				member.setMemId(rs.getString("memId"));
				member.setMemPass(rs.getString("memPass"));
				member.setMemEmail(rs.getString("memEmail"));
				member.setMemAddr1(rs.getString("memAddr1"));
				member.setMemAddr2(rs.getString("memAddr2"));
				memberList.add(member);
			}
		} catch (Exception e) {
			System.out.println("getMemberList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return memberList;
	}
	
	public Member getMemberInfo(String id, String index) {
		//회원 정보보기(통합관리자용)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member member = null;
		String query = "SELECT * FROM view_member_info where memIndex = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, index);
			rs = pstmt.executeQuery();
			if (rs.next()) { //회원정보는 member에 저장하고
				member = new Member();
				member.setMemIndex(rs.getString("memIndex"));
				member.setMemName(rs.getString("memName"));
				member.setMemBirth(rs.getString("memBirth"));
				member.setMemZip(rs.getString("memZip"));
				member.setMemTel(rs.getString("memTel"));
				member.setMemCarNum(rs.getString("memCarNum"));
				member.setMemGrade(rs.getString("memGrade"));
				member.setMemId(rs.getString("memId"));
				member.setMemPass(rs.getString("memPass"));
				member.setMemEmail(rs.getString("memEmail"));
				member.setMemAddr1(rs.getString("memAddr1"));
				member.setMemAddr2(rs.getString("memAddr2"));
				rs.beforeFirst(); //커서를 처음위치로 변환한 후에
				while (rs.next()) { //회원의 예약, 대출, 연체내역을 while로 돌린다.
					member.setMemRenFlag(rs.getString("renFlag"));
					member.setBookName(rs.getString("bookName"));
					member.setLibName(rs.getString("libName"));
					member.setRenRevDate(rs.getString("renRevDate"));
					member.setRenRevInvDate(rs.getString("renRevInvDate"));
					member.setRenBrwDate(rs.getString("renBrwDate"));
					member.setRenBrwInvDate(rs.getString("renBrwInvDate"));
					member.setRenIdvDelFlag(rs.getString("renIdvDelFlag"));
				}
			}
			for (String a : member.getBookName()) {
				System.out.println("a : " + a);
			}
			/* 
			 * 회원정보부분, 별 문제 없으면 지워도 됨.. 
			query = "SELECT * FROM view_member_renidv where memIndex = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, index);
			rs = pstmt.executeQuery();
			while (rs.next()) { //회원의 예약, 대출, 연체 카운트를 가져옴
				member.setMemRevCount(rs.getString("rev"));
				member.setMemBrwCount(rs.getString("brw"));
				member.setMemOverdueCount(rs.getString("overdue"));
			}
			*/
		} catch (Exception e) {
			System.out.println("getMemberInfo 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return member;
	}
	
	public ArrayList<Admin> getAdminList() {
		//통합관리자 리스트(통합관리자용)
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Admin admin = null;
		ArrayList<Admin> adminList = new ArrayList<Admin>();
		String query = "select * from admin where adminGrade = 'C'";
		try {
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				admin = new Admin();
				admin.setAdminNum(rs.getString("adminNum"));
				admin.setAdminName(rs.getString("adminName"));
				admin.setAdminTel(rs.getString("adminTel"));
				admin.setAdminGrade(rs.getString("adminGrade"));
				admin.setAdminId(rs.getString("adminId"));
				admin.setAdminPassword(rs.getString("adminPass"));
				admin.setAdminEmail(rs.getString("adminEmail"));
				adminList.add(admin);
			}
		} catch (Exception e) {
			System.out.println("getAdminList 에러 : " + e);
		} finally {
			if (rs != null) close(rs);
			if (pstmt != null) close(rs);
		}
		return adminList;
	}

	public int updateMemberInfo(String index, Member member) {
		// TODO Auto-generated method stub
		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql = "update member set memId = ?, memPass = ?, memName = ?, memBirth = ?, memTel = ?, memCarNum = ?, memZip = ?, memAddr1 = ?, memAddr2 = ?, memEmail = ? where memIndex = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMemId());
			pstmt.setString(2, member.getMemPass());
			pstmt.setString(3, member.getMemName());
			pstmt.setString(4, member.getMemBirth());
			pstmt.setString(5, member.getMemTel());
			pstmt.setString(6, member.getMemCarNum());
			pstmt.setString(7, member.getMemZip());
			pstmt.setString(8, member.getMemAddr1());
			pstmt.setString(9, member.getMemAddr2());
			pstmt.setString(10, member.getMemEmail());
			pstmt.setString(11, index);
			updateCount = pstmt.executeUpdate(); 
		} catch (Exception e) {
			System.out.println("updateMemberInfo 에러 : " + e);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		
		return updateCount;
	}

	public int deleteMember(String memIndex) {
		// TODO Auto-generated method stub
		int deleteCount = 0;
		PreparedStatement pstmt = null;
		String query = "delete from member where memIndex = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memIndex);
			deleteCount = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("deleteMember 에러 ::::: " + e);
		} finally {
			if (pstmt != null) close(pstmt);
		}
		return deleteCount;
	}
}