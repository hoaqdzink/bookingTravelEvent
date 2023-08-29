package booking.travel.service.lmpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import booking.travel.dao.ReportDAO;
import booking.travel.dto.Report;
import booking.travel.dto.ReportCountTranport;
import booking.travel.dto.ReportCountType;
import booking.travel.dto.ReportTop;
import booking.travel.dto.ReportTop2;
import booking.travel.service.ReportService;

@Service
public class ReportServicelmpl implements ReportService{

	@Autowired
	ReportDAO reportDao;

	@Override
	public List<Report> getReportDoanhThu(String username, int year) {
		List<Report> list1 = reportDao.getReportDoanhThuTour(username, year);
		List<Report> list2 = reportDao.getReportDoanhThuEvent(username, year);
		List<Report> list3 = new ArrayList<>();
		for(int i=1; i<=12; i++) {
			final Integer month = i;
			Optional<Report> r1 = list1.stream().filter(m -> m.getMonth() == month).findFirst();
			Optional<Report> r2 = list2.stream().filter(m -> m.getMonth() == month).findFirst();
			Report r3 = new Report(i, r1.isPresent()?r1.get().getDoanhThuTour():0.0, r2.isPresent()?r2.get().getDoanhThuEvent():0.0);
			list3.add(r3);
		}
		return list3;
	}

	@Override
	public List<ReportCountType> getSoLuongTour() {
		return reportDao.getSoLuongTourWithType();
	}
	
	@Override
	public List<ReportCountTranport> getSoLuongTranport() {
		return reportDao.getSoLuongTourWithTranport();
	}

	@Override
	public List<ReportTop> getReportTopEnterpriseTour() {
		Pageable pageable = PageRequest.of(0, 10);
		List<ReportTop> list1 = reportDao.getReportTopEnterpriseTour(pageable);
		return list1;
	}
	
	@Override
	public List<ReportTop2> getReportTopEnterpriseEvent() {
		Pageable pageable = PageRequest.of(0, 10);
		List<ReportTop2> list2 = reportDao.getReportTopEnterpriseEvent(pageable);
		return list2;
	}


}
