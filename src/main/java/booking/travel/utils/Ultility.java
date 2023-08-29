package booking.travel.utils;

import javax.servlet.http.HttpServletRequest;

public class Ultility {
	public static String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();////LẤY ĐƯỢC ĐƯỜNG DẪN http://localhost:8080/security_forgot_password
		return siteURL.replace(request.getServletPath(), "");////RETURN NÀY TRẢ VỀ ĐƯỜNG DẪN GỐC SERVER LÀ http://localhost:8080
	}
}
