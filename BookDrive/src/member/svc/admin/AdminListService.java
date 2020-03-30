package member.svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import member.dao.admin.MemberDAO;
import vo.admin.Admin;

public class AdminListService {
	public ArrayList<Admin> selectAdminList() {
		// TODO Auto-generated method stub
		ArrayList<Admin> adminList = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		adminList = memberDAO.getAdminList();
		if (con != null) close(con);
		return adminList;
	}

}
