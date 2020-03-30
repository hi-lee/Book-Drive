package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static db.JdbcUtil.*;

import member.vo.Member;

public class MemberDAO {
	Connection con;
	private static MemberDAO memberDAO;

	public static MemberDAO getInstance() {
		// TODO Auto-generated method stub
		if (memberDAO == null) {
			memberDAO = new MemberDAO();
		}
		return memberDAO;
	}

	public void setConnection(Connection con) {
		// TODO Auto-generated method stub
		this.con = con;

	}

	public Member selectMember(String id) {
		// TODO Auto-generated method stub
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from member where memId=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setIndex(rs.getString("memIndex"));
				member.setId(rs.getString("memId"));
				member.setPassword(rs.getString("memPass"));
				member.setName(rs.getString("memName"));
				member.setBirth(rs.getString("memBirth"));
				member.setZip(rs.getString("memZip"));
				member.setAddr1(rs.getString("memAddr1"));
				member.setAddr2(rs.getString("memAddr2"));
				member.setTel(rs.getString("memTel"));
				member.setEmail(rs.getString("memEmail"));
				member.setCarNum(rs.getString("memCarNum"));
				member.setGrade(rs.getString("memGrade"));
			}
		} catch (SQLException e) {
			System.out.println("login err : " + e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return member;
	}

	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int insertCount = 0;
		try {
			pstmt = con.prepareStatement("insert into member values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'N')");
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getBirth());
			pstmt.setString(5, member.getZip());
			pstmt.setString(6, member.getAddr1());
			pstmt.setString(7, member.getAddr2());
			pstmt.setString(8, member.getTel());
			pstmt.setString(9, member.getEmail());
			pstmt.setString(10, member.getCarNum());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("memberJoin 에러 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertCount;
	}

	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		int updateCount = 0;
		try {
			pstmt = con.prepareStatement(
					"update member set memPass=?, memBirth=?, memZip=?, memAddr1=?, memAddr2=?, memTel=?, memEmail=?, memCarNum=?");
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getBirth());
			pstmt.setString(3, member.getZip());
			pstmt.setString(4, member.getAddr1());
			pstmt.setString(5, member.getAddr2());
			pstmt.setString(6, member.getTel());
			pstmt.setString(7, member.getEmail());
			pstmt.setString(8, member.getCarNum());
			updateCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("update 에러 : " + e);
		}
		return updateCount;
	}

	public Member getMemberId(String name, String birth) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member memberId = null;

		try {
			pstmt = con.prepareStatement("select memId, memName from member where memName = ? and memBirth = ?");
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				memberId = new Member();
				memberId.setId(rs.getString("memId"));
				memberId.setName(rs.getString("memName"));
			}
		} catch (SQLException e) {
			System.out.println("getMemId err   :" + e);
		}
		return memberId;
	}

	public int getMemInfo(String id, String pw, String receiver) {
		// TODO Auto-generated method stub
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int updateCount = 0;
		try {
			pstmt = con.prepareStatement("select * from member where memId = ? and memEmail = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, receiver);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt = con.prepareStatement("update member set memPass = ? where memId = ? ");
				pstmt.setString(1, pw);
				pstmt.setString(2, id);
				updateCount = pstmt.executeUpdate();

				if (updateCount > 0)
					commit(con);
			}
		} catch (SQLException e) {
			System.out.println("getMemInfo Err   :  " + e);
		}
		return updateCount;
	}

	public Member selectDriveMember(String memIndex) {
		// TODO Auto-generated method stub
		Member member = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from member where memIndex = ? OR memCarNum = ? ");
			pstmt.setString(1, memIndex);
			pstmt.setString(2, memIndex);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new Member();
				member.setIndex(rs.getString("memIndex"));
				member.setId(rs.getString("memId"));
				member.setPassword(rs.getString("memPass"));
				member.setName(rs.getString("memName"));
				member.setBirth(rs.getString("memBirth"));
				member.setZip(rs.getString("memZip"));
				member.setAddr1(rs.getString("memAddr1"));
				member.setAddr2(rs.getString("memAddr2"));
				member.setTel(rs.getString("memTel"));
				member.setEmail(rs.getString("memEmail"));
				member.setCarNum(rs.getString("memCarNum"));
				member.setGrade(rs.getString("memGrade"));
			}
		} catch (Exception e) {
			System.out.println("selectDriveMember Err :  " + e);
		}
		return member;
	}
}