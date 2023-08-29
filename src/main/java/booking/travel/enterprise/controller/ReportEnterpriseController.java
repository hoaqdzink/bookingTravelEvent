package booking.travel.enterprise.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import booking.travel.dto.Report;
import booking.travel.service.ReportService;

@Controller
@RequestMapping("/enterprise/")
public class ReportEnterpriseController {

	@Autowired
	ReportService reportService;
	
	@RequestMapping("report")
	public String reportEnterprise() {
		return "enterprise/report/doanhThu";
	}
	
	@ResponseBody
	@RequestMapping("api_report_doanh_thu")
	public List<Report> reportApi(HttpServletRequest request) {
		String username = request.getRemoteUser();
		Integer year = Integer.parseInt(request.getParameter("year"));
		List<Report> list = reportService.getReportDoanhThu(username, year);
		return list;
	}
}
