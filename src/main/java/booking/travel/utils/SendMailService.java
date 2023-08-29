package booking.travel.utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import booking.travel.entity.Account;

@Service
public class SendMailService {

	@Autowired
	JavaMailSender mailSender;
	
	/////////////////////-------------------------GỬI EMAIL XÁC NHẬN TÀI KHOẢN-----------------------------//////////////////
	////----MAIL XÁC NHẬN TÀI KHOẢN DOANH NGHIỆP
	public String sendVerificationEmailEnterprise(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException {
		
		String toAddress = account.getEmail();
		String fromAddress = "bookingtravel.event@gmail.com";
		String senderName = "Booking Travel & Event";
		String subject = "Xác nhận thông tin tài khoản";//tiêu đề
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "Gửi doanh nghiệp <b>[[name]]</b>,<br>"//content là nội dung tin nhắn bên trong thư
				+ "Vui lòng chọn vào liên kết bên dưới để xác nhận đăng kí:<br>"
				+ "<h3><a style=\"padding: 6px 30px 10px 30px;\r\n"
				+ "    text-decoration: none;\r\n"
				+ "    background-color: #36c05e;\r\n"
				+ "    border-radius: 5px;\r\n"
				+ "    text-align: center;\r\n"
				+ "    vertical-align: middle;\r\n"
				+ "    color: white;\" href=\"[[URL]]\" target=\"_self\">Xác nhận</a></h3>"
				+ "Cảm ơn,<br>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);
		
		content = content.replace("[[name]]", account.getFullname());
		String verifyURL = siteURL + "/verify?code=" + account.getVerificationCode();/// siteURL là http://localhost:8080
		
		content = content.replace("[[URL]]", verifyURL);
		
		helper.setText(content, true);
		mailSender.send(message);
		
		return null;
	}
	
	////--MAIL XÁC NHẬN TÀI KHOẢN KHÁCH HÀNG
	public String sendVerificationEmailCustomer(Account account, String siteURL) throws MessagingException, UnsupportedEncodingException {
		
		String toAddress = account.getEmail();
		String fromAddress = "bookingtravel.event@gmail.com";
		String senderName = "Booking Travel & Event";
		String subject = "Xác nhận thông tin tài khoản";//tiêu đề
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "Gửi bạn <b>[[name]]</b>,<br>"//content là nội dung tin nhắn bên trong thư
				+ "Vui lòng chọn vào liên kết bên dưới để xác nhận đăng kí:<br>"
				+ "<h3><a style=\"padding: 6px 30px 10px 30px;\r\n"
				+ "    text-decoration: none;\r\n"
				+ "    background-color: #36c05e;\r\n"
				+ "    border-radius: 5px;\r\n"
				+ "    text-align: center;\r\n"
				+ "    vertical-align: middle;\r\n"
				+ "    color: white;\" href=\"[[URL]]\" target=\"_self\">Xác nhận</a></h3>"
				+ "Cảm ơn,<br>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);
		
		content = content.replace("[[name]]", account.getFullname());
		String verifyURL = siteURL + "/verify?code=" + account.getVerificationCode();
		
		content = content.replace("[[URL]]", verifyURL);
		
		helper.setText(content, true);
		mailSender.send(message);
		
		return null;
	}
	
	/////////////////////-------------------------GỬI EMAIL QUÊN MẬT KHẨU-----------------------------//////////////////
	public String sendMailForgotPassword(Account account, String siteURL) throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("bookingtravel.event@gmail.com", "Booking Travel & Event");
		helper.setTo(account.getEmail());
		
		String subject = "Liên kết khôi phục mật khẩu";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào! </p>"
				+ "<p>Bạn có một yêu cầu khôi phục mật khẩu, chọn vào liên kết bên dưới để khôi phục:</p>"
				+ "<p><b><a style=\"text-decoration: none;\r\n"
				+ "    padding: 6px 20px 10px 20px;\r\n"
				+ "    background-color: #36c05e;\r\n"
				+ "    color: white;\r\n"
				+ "    border-radius: 5px;\r\n"
				+ "    text-align: center;\r\n"
				+ "    vertical-align: middle;\" href=\"[[URL]]\"> Thay đổi mật khẩu</a></b></p>"
				+ "<p>Bỏ qua tin nhắn nếu bạn đã nhớ mật khẩu của mình</p>"
				+ "<p>Cảm ơn,</p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		helper.setSubject(subject);
		content = content.replace("[[URL]]", siteURL+"/reset_password?token="+account.getResetPasswordToken());
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
	}

	/////////////////////-------------------------GỬI EMAIL ĐẶT DỊCH VỤ-----------------------------//////////////////
	////---MAIL KHI DUYỆT ĐƠN ĐẶT TOUR
	public String sendMailOrderTourDuyet(String email, String emailEnterprise, String nameEnterprise, 
			String orderTourId, String sumPrice, String slNguoiLon, String slTreEm, String slEmBe, String discount, String payment) 
					throws MessagingException, UnsupportedEncodingException{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(emailEnterprise, "Booking Travel & Event");
		helper.setTo(email);
		
		String subject = "Thông báo tình trạng đơn đặt";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Đơn đặt của bạn thuộc loại tour đã được duyệt</p>"
				+ "<table border='1' style=\"width: 100%; text-align: center;  border-collapse: collapse; font-family: sans-serif; font-size: 14px;\">"
					+ "<thead style=\"background-color:orange; color:white\">"
						+ "<tr>"
							+ "<th style=\"padding: 8px 13px;\">Mã đơn đặt</th>"
							+ "<th>Tên doanh nghiệp</th>"
							+ "<th>Tổng tiền</th>"
							+ "<th>S.L người lớn</th>"
							+ "<th>S.L trẻ em</th>"
							+ "<th>S.L em bé</th>"
							+ "<th>Khuyến mãi</th>"
							+ "<th>Phương thức</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
						+ "<tr>"
							+ "<td style=\"padding: 8px 13px;\">[[orderTourId]]</td>"
							+ "<td>[[nameEnterprise]]</td>"
							+ "<td>[[sumPrice]]</td>"
							+ "<td>[[slNguoiLon]]</td>"
							+ "<td>[[slTreEm]]</td>"
							+ "<td>[[slEmBe]]</td>"
							+ "<td>[[discount]]%</td>"
							+ "<td>[[payment]]</td>"
						+ "</tr>"
					+ "</tbody>"
				+ "</table>"
				+ "<p>Cảm ơn, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[orderTourId]]", orderTourId);
		content = content.replace("[[nameEnterprise]]", nameEnterprise);
		content = content.replace("[[sumPrice]]", sumPrice);
		content = content.replace("[[slNguoiLon]]", slNguoiLon);
		content = content.replace("[[slTreEm]]", slTreEm);
		content = content.replace("[[slEmBe]]", slEmBe);
		content = content.replace("[[discount]]", discount);
		content = content.replace("[[payment]]", payment);
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
		
	}

	////---MAIL KHI HỦY ĐƠN ĐẶT TOUR
	public String sendMailOrderTourHuy(String email, String emailEnterprise, String nameEnterprise, String orderTourId, 
			String sumPrice, String slNguoiLon, String slTreEm, String slEmBe, String discount, String payment) 
					throws MessagingException, UnsupportedEncodingException{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(emailEnterprise, "Booking Travel & Event");
		helper.setTo(email);
		
		String subject = "Thông báo tình trạng đơn đặt";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Đơn đặt của bạn thuộc loại tour đã bị hủy</p>"
				+ "<table border='1' style=\"width: 100%; text-align: center;  border-collapse: collapse; font-family: sans-serif; font-size: 14px;\">"
					+ "<thead style=\"background-color:orange; color:white\">"
						+ "<tr>"
							+ "<th style=\"padding: 8px 13px;\">Mã đơn đặt</th>"
							+ "<th>Tên doanh nghiệp</th>"
							+ "<th>Tổng tiền</th>"
							+ "<th>S.L người lớn</th>"
							+ "<th>S.L trẻ em</th>"
							+ "<th>S.L em bé</th>"
							+ "<th>Khuyến mãi</th>"
							+ "<th>Phương thức</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
						+ "<tr>"
							+ "<td style=\"padding: 8px 13px;\">[[orderTourId]]</td>"
							+ "<td>[[nameEnterprise]]</td>"
							+ "<td>[[sumPrice]]</td>"
							+ "<td>[[slNguoiLon]]</td>"
							+ "<td>[[slTreEm]]</td>"
							+ "<td>[[slEmBe]]</td>"
							+ "<td>[[discount]]%</td>"
							+ "<td>[[payment]]</td>"
						+ "</tr>"
					+ "</tbody>"
				+ "</table>"
				+ "<p>Cảm ơn, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[orderTourId]]", orderTourId);
		content = content.replace("[[nameEnterprise]]", nameEnterprise);
		content = content.replace("[[sumPrice]]", sumPrice);
		content = content.replace("[[slNguoiLon]]", slNguoiLon);
		content = content.replace("[[slTreEm]]", slTreEm);
		content = content.replace("[[slEmBe]]", slEmBe);
		content = content.replace("[[discount]]", discount);
		content = content.replace("[[payment]]", payment);
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
		
	}
	
	////---MAIL KHI DUYỆT ĐƠN ĐẶT EVENT
	public String sendMailOrderEventDuyet(String email, String emailEnterprise, String nameEnterprise, String orderEventId, 
			String sumPrice, String slNguoiLon, String slTreEm, String slEmBe, String discount, String payment) 
					throws MessagingException, UnsupportedEncodingException{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(emailEnterprise, "Booking Travel & Event");
		helper.setTo(email);
		
		String subject = "Thông báo tình trạng đơn đặt";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Đơn đặt của bạn thuộc loại sự kiện đã được duyệt</p>"
				+ "<table border='1' style=\"width: 100%; text-align: center;  border-collapse: collapse;\">"
					+ "<thead style=\"background-color:orange; color:white\">"
						+ "<tr>"
							+ "<th style=\"padding: 8px 13px;\">Mã đơn đặt</th>"
							+ "<th>Tên doanh nghiệp</th>"
							+ "<th>Tổng tiền</th>"
							+ "<th>S.L người lớn</th>"
							+ "<th>S.L trẻ em</th>"
							+ "<th>S.L em bé</th>"
							+ "<th>Khuyến mãi</th>"
							+ "<th>Phương thức</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
						+ "<tr>"
							+ "<td style=\"padding: 8px 13px;\">[[orderTourId]]</td>"
							+ "<td>[[nameEnterprise]]</td>"
							+ "<td>[[sumPrice]]</td>"
							+ "<td>[[slNguoiLon]]</td>"
							+ "<td>[[slTreEm]]</td>"
							+ "<td>[[slEmBe]]</td>"
							+ "<td>[[discount]]%</td>"
							+ "<td>[[payment]]</td>"
						+ "</tr>"
					+ "</tbody>"
				+ "</table>"
				+ "<p>Cảm ơn, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[orderTourId]]", orderEventId);
		content = content.replace("[[nameEnterprise]]", nameEnterprise);
		content = content.replace("[[sumPrice]]", sumPrice);
		content = content.replace("[[slNguoiLon]]", slNguoiLon);
		content = content.replace("[[slTreEm]]", slTreEm);
		content = content.replace("[[slEmBe]]", slEmBe);
		content = content.replace("[[discount]]", discount);
		content = content.replace("[[payment]]", payment);
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
		
	}

	///---MAIL KHI HỦY ĐƠN ĐẶT EVENT
	public String sendMailOrderEventHuy(String email, String emailEnterprise, String nameEnterprise, String orderEventId,
			String sumPrice, String slNguoiLon, String slTreEm, String slEmBe, String discount, String payment) 
					throws MessagingException, UnsupportedEncodingException{
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(emailEnterprise, "Booking Travel & Event");
		helper.setTo(email);
		
		String subject = "Thông báo tình trạng đơn đặt";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Đơn đặt của bạn thuộc loại sự kiện đã bị hủy</p>"
				+ "<table border='1' style=\"width: 100%; text-align: center;  border-collapse: collapse;\">"
					+ "<thead style=\"background-color:orange; color:white\">"
						+ "<tr>"
							+ "<th style=\"padding: 8px 13px;\">Mã đơn đặt</th>"
							+ "<th>Tên doanh nghiệp</th>"
							+ "<th>Tổng tiền</th>"
							+ "<th>S.L người lớn</th>"
							+ "<th>S.L trẻ em</th>"
							+ "<th>S.L em bé</th>"
							+ "<th>Khuyến mãi</th>"
							+ "<th>Phương thức</th>"
						+ "</tr>"
					+ "</thead>"
					+ "<tbody>"
						+ "<tr>"
							+ "<td style=\"padding: 8px 13px;\">[[orderTourId]]</td>"
							+ "<td>[[nameEnterprise]]</td>"
							+ "<td>[[sumPrice]]</td>"
							+ "<td>[[slNguoiLon]]</td>"
							+ "<td>[[slTreEm]]</td>"
							+ "<td>[[slEmBe]]</td>"
							+ "<td>[[discount]]%</td>"
							+ "<td>[[payment]]</td>"
						+ "</tr>"
					+ "</tbody>"
				+ "</table>"
				+ "<p>Cảm ơn, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[orderTourId]]", orderEventId);
		content = content.replace("[[nameEnterprise]]", nameEnterprise);
		content = content.replace("[[sumPrice]]", sumPrice);
		content = content.replace("[[slNguoiLon]]", slNguoiLon);
		content = content.replace("[[slTreEm]]", slTreEm);
		content = content.replace("[[slEmBe]]", slEmBe);
		content = content.replace("[[discount]]", discount);
		content = content.replace("[[payment]]", payment);
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
		
	}

	/////////////////////-------------------------GỬI EMAIL DUYỆT HOẶC HỦY BÀI VIẾT-----------------------------//////////////////
	////--MAIL KHI DUYỆT BÀI VIẾT TOUR
	public String sendMailDuyetTour(String email, String tenBai, String tourId) throws MessagingException, UnsupportedEncodingException{
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("bookingtravel.event@gmail.com", "Booking Travel & Event");
		helper.setTo(email);
		
		String subject = "Thông báo tình trạng bài viết";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Bài viết <b>[[name]]</b> có mã <b>[[tourId]]</b> thuộc loại tour của bạn đã được duyệt thành công</p>"
				+ "<p>Cảm ơn, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[name]]", tenBai);
		content = content.replace("[[tourId]]", tourId);
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
		
	}

	///---MAIL KHI HỦY BÀI VIẾT TOUR
	public String sendMailHuyTour(String email, String tenBai, String tourId) throws MessagingException, UnsupportedEncodingException{
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("bookingtravel.event@gmail.com", "Booking Travel & Event");
		helper.setTo(email);
		
		String subject = "Thông báo tình trạng bài viết";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Bài viết <b>[[name]]</b> có mã <b>[[tourId]]</b> thuộc loại tour của bạn đã bị hủy</p>"
				+ "<p>Cảm ơn, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[name]]", tenBai);
		content = content.replace("[[tourId]]", tourId);
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
		
	}
	
	////---MAIL KHI DUYỆT BÀI VIẾT EVENT
	public String sendMailDuyetEvent(String email, String tenBai, String eventPostId) throws MessagingException, UnsupportedEncodingException{
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("bookingtravel.event@gmail.com", "Booking Travel & Event");
		helper.setTo(email);
		
		String subject = "Thông báo tình trạng bài viết";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Bài viết <b>[[name]]</b> có mã <b>[[eventPostId]]</b> thuộc loại sự kiện của bạn đã được duyệt thành công</p>"
				+ "<p>Cảm ơn, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[name]]", tenBai);
		content = content.replace("[[eventPostId]]", eventPostId);
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
		
	}

	///----MAIL KHI HỦY BÀI VIẾT EVENT
	public String sendMailHuyEvent(String email, String tenBai, String eventPostId) throws MessagingException, UnsupportedEncodingException{
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("bookingtravel.event@gmail.com", "Booking Travel & Event");
		helper.setTo(email);
		
		String subject = "Thông báo tình trạng bài viết";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Bài viết <b>[[name]]</b> có mã <b>[[eventPostId]]</b> thuộc loại sự kiện của bạn đã bị hủy</p>"
				+ "<p>Cảm ơn, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[name]]", tenBai);
		content = content.replace("[[eventPostId]]", eventPostId);
		
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
		
	}

	public String sendMailReplyUser(String emailUser, String emailEnterprise, String replyUser) throws MessagingException, UnsupportedEncodingException{

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(emailEnterprise, "Booking Travel & Event");
		helper.setTo(emailUser);
		
		String subject = "Thông báo phản hồi";
		
		String content = "<div style=\"font-family: sans-serif; font-size:16px\">"
				+ "<p>Xin chào </p>"
				+ "<p>Phản hồi của bạn chúng tôi đã tiếp nhận và kèm theo lời nhắn</p>"
				+ "<textarea readonly cols=\"35\" rows=\"7\">[[replyUser]]</textarea>"
				+ "<p>Cảm ơn bạn đã sử dụng dịch vụ, </p>"
				+ "<p style=\"color:#8a8d91\">© 2021 Booking Travel & Event Inc. All Rights Reserved</p>"
				+ "</div>";
		
		content = content.replace("[[replyUser]]", replyUser);
		helper.setSubject(subject);
		helper.setText(content, true);
		
		mailSender.send(message);
		
		return null;
	}
}
