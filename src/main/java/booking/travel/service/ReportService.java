package booking.travel.service;

import java.util.List;

import booking.travel.dto.Report;
import booking.travel.dto.ReportCountTranport;
import booking.travel.dto.ReportCountType;
import booking.travel.dto.ReportTop;
import booking.travel.dto.ReportTop2;

public interface ReportService {

	List<Report> getReportDoanhThu(String username, int year);

	List<ReportCountType> getSoLuongTour();

	List<ReportTop> getReportTopEnterpriseTour();

	List<ReportTop2> getReportTopEnterpriseEvent();

	List<ReportCountTranport> getSoLuongTranport();
}
