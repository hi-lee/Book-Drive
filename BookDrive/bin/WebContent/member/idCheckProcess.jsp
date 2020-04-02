<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String chk_id = request.getParameter("id");
		if (chk_id == null || chk_id.equals("")) {
			out.println("<script>");
			out.println("location.href='idCheck.jsp'");
			out.println("</script>");
		} else {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Context init = new InitialContext();
				DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
				conn = ds.getConnection();

				pstmt = conn.prepareStatement("select * from member where id=?");
				pstmt.setString(1, chk_id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
				
				//	out.println("<script>");
				//	out.println("location.href='idCheck.jsp?id=" + chk_id + "&useble=no'");
				//	out.println("</script>");					
					response.sendRedirect("idCheck.jsp?chk_id="+chk_id+"&useble=no");
				} else {
				//	out.println("<script>");
				//	out.println("location.href='idCheck.jsp?id=" + chk_id + "&useble=yes'");
				//	out.println("</script>");
					response.sendRedirect("idCheck.jsp?chk_id="+chk_id+"&useble=yes");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(rs!=null)
						rs.close();
					if(pstmt!=null)
						pstmt.close();
					if(conn!=null)
						conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	%>
</body>
</html>