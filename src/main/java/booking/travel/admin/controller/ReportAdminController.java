package booking.travel.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import booking.travel.dto.ReportCountTranport;
import booking.travel.dto.ReportCountType;
import booking.travel.dto.ReportTop;
import booking.travel.dto.ReportTop2;
import booking.travel.service.ReportService;

@Controller
@RequestMapping("/admin/")
public class ReportAdminController {

	@Autowired
	ReportService reportService;
	
	@RequestMapping("report")
	public String report() {
		return "admin/report/soLuong";
	}
	
	@RequestMapping("reportTop")
	public String reportTop() {
		return "admin/report/reportTop";
	}
	
	@ResponseBody
	@RequestMapping("api_reportCount")
	public List<ReportCountType> reportCountApi(){
		return reportService.getSoLuongTour();
	}
	
	@ResponseBody
	@RequestMapping("api_reportCountTranport")
	public List<ReportCountTranport> reportCountTranport(){
		return reportService.getSoLuongTranport();
	}
	
	@ResponseBody
	@RequestMapping("api_report_top_enterprise_tour")
	public List<ReportTop> topEnterpriseTour(){
		return reportService.getReportTopEnterpriseTour();
	}
	
	@ResponseBody
	@RequestMapping("api_report_top_enterprise_event")
	public List<ReportTop2> topEnterpriseEvent(){
		return reportService.getReportTopEnterpriseEvent();
	}
}
