package booking.travel.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking.travel.dto.Report;
import booking.travel.dto.ReportCountTranport;
import booking.travel.dto.ReportCountType;
import booking.travel.dto.ReportTop;
import booking.travel.dto.ReportTop2;
import booking.travel.entity.TourPost;

public interface ReportDAO extends JpaRepository<TourPost, String>{

	@Query("SELECT new Report(MONTH(o.createDate), "
			+ "sum((o.priceTour+(o.priceAdult*o.quantityAdult + o.priceChildren*o.quantityChildren + o.priceBaby*o.quantityBaby))"
			+ "-(o.priceTour+(o.priceAdult*o.quantityAdult + o.priceChildren*o.quantityChildren + o.priceBaby*o.quantityBaby))*o.discount/100), 0.0) "
			+ "FROM OrderTour o "
			+ "WHERE (o.tourPost.account.username = ?1) AND o.status = 'DUYET' AND YEAR(o.createDate) = ?2 "
			+ "GROUP BY MONTH(o.createDate)")
	public List<Report> getReportDoanhThuTour(String username, int year);
	
	@Query("SELECT new Report(MONTH(o.createDate),0.0, "
			+ "sum((o.priceEvent+(o.priceAdult*o.quantityAdult + o.priceChildren*o.quantityChildren + o.priceBaby*o.quantityBaby))"
			+ "-(o.priceEvent+(o.priceAdult*o.quantityAdult + o.priceChildren*o.quantityChildren + o.priceBaby*o.quantityBaby))*o.discount/100)) "
			+ "FROM OrderEvent o "
			+ "WHERE (o.eventPost.account.username = ?1) AND o.status = 'DUYET' AND YEAR(o.createDate) = ?2 "
			+ "GROUP BY MONTH(o.createDate)")
	public List<Report> getReportDoanhThuEvent(String username, int year);
	
	@Query("SELECT new ReportCountType(t.type.mainName, count(t)) "
			+ "FROM TourPost t "
			+ "GROUP BY t.type.mainName")
	public List<ReportCountType> getSoLuongTourWithType();
	
	@Query("SELECT new ReportCountTranport(t.tranport.name, count(t)) "
			+ "FROM TourPost t "
			+ "GROUP BY t.tranport.name")
	public List<ReportCountTranport> getSoLuongTourWithTranport();
	
	
	@Query("SELECT new ReportTop(o.tourPost.account.fullname, "
			+ "sum(o.priceTour+(o.priceAdult*o.quantityAdult + o.priceChildren*o.quantityChildren + o.priceBaby*o.quantityBaby))) "
			+ "FROM OrderTour o "
			+ "WHERE o.status = 'DUYET' "
			+ "GROUP BY o.tourPost.account.fullname "
			+ "ORDER BY sum(o.priceTour+(o.priceAdult*o.quantityAdult + o.priceChildren*o.quantityChildren + o.priceBaby*o.quantityBaby)) DESC")
	public List<ReportTop> getReportTopEnterpriseTour(Pageable pageable);
	
	@Query("SELECT new ReportTop2(o.eventPost.account.fullname, "
			+ "sum(o.priceEvent+(o.priceAdult*o.quantityAdult + o.priceChildren*o.quantityChildren + o.priceBaby*o.quantityBaby))) "
			+ "FROM OrderEvent o "
			+ "WHERE o.status = 'DUYET' "
			+ "GROUP BY o.eventPost.account.fullname "
			+ "ORDER BY sum(o.priceEvent+(o.priceAdult*o.quantityAdult + o.priceChildren*o.quantityChildren + o.priceBaby*o.quantityBaby)) DESC")
	public List<ReportTop2> getReportTopEnterpriseEvent(Pageable pageable);
}
