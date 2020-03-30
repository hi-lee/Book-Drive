package member.action;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import mail.GoogleAuthentication;
import mail.randomPw;
import member.svc.MailSendSvc;
import member.vo.Member;

public class pwMailSendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("userID");
		String sender = "admin@bookDrive.com";
		String receiver = request.getParameter("userEmailId") + "@" + request.getParameter("userEmailAd");
		String subject = "[Book-Drive] 임시비밀번호 발송 메일입니다.";
		String content = "임시비밀번호 입니다. 로그인 후 변경하여 사용하세요. \r";
		String pw = randomPw.randomPass(10);
		
		MailSendSvc mailSendSvc = new MailSendSvc();
		int updateCount = mailSendSvc.getMemInfo(id, pw, receiver);
		
		if(updateCount == '0') { 
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('아이디, 메일주소를 입력하세요')");
			out.print("history.back();");
			out.print("</script>");
		} else { System.out.println("id :    " + id);
			response.setContentType("text/html;charset=utf-8");
			
			content += "임시비밀번호 : " + pw + "\r";
			content += "<a href='#'>로그인</a>";
			
			try {
				Properties properties = System.getProperties();
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.port", "587");
				GoogleAuthentication auth = new GoogleAuthentication();
				Session s = Session.getDefaultInstance(properties, auth);
				Message message = new MimeMessage(s);
				Address sender_address = new InternetAddress(sender);
				Address receiver_address = new InternetAddress(receiver);
				message.setHeader("content-type", "text/html;charset=utf-8");
				message.setFrom(sender_address);
				message.addRecipient(Message.RecipientType.TO, receiver_address);
				message.setSubject(subject);
				message.setContent(content, "text/html;charset=utf-8");
				message.setSentDate(new java.util.Date());
				Transport.send(message);
				
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('메일이 정상적으로 전송되었습니다.');");
				out.print("window.close();");
				out.print("</script>");

			} catch (Exception e) {
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("alert('SMTP 서버가 잘못 설정되었거나, 서비스에 문제가 있습니다.');");
				out.print("history.back();");
				out.print("</script>");
			}
		}
		return forward;
	}

}
