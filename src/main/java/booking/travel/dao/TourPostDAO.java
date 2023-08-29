package booking.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking.travel.entity.TourPost;

public interface TourPostDAO extends JpaRepository<TourPost, String>{

	//ĐỔ TẤT CẢ TOUR THEO LOẠI VÀ DUYỆT
	@Query("SELECT t FROM TourPost t WHERE"
			+ " t.status = 'DUYET' AND t.isDeleted = false AND t.type.typeId = ?1")
	List<TourPost> findByTypeId(String tid);
	
	@Query("SELECT t FROM TourPost t WHERE"
			+ " t.status = 'DUYET' AND t.isDeleted = false AND t.tranport.tranportId = ?1")
	List<TourPost> findByTranportId(String trid);
	
	//ĐỔ TẤT CẢ TOUR ĐÃ ĐƯỢC ĐẨY BÀI, DUYỆT VÀ GIẢM DẦN THEO NGÀY
	@Query("SELECT t FROM TourPost t WHERE"
			+ " t.status = 'DUYET' AND t.isDeleted = false AND t.topPost = true"
			+ " ORDER BY t.createDate DESC")
	List<TourPost> findByTopPost();
	
	//ĐỔ TẤT CẢ TOUR ĐÃ DUYỆT VÀ GIẢM DẦN THEO NGÀY
	@Query("SELECT t FROM TourPost t WHERE"
			+ " t.status = 'DUYET' AND t.isDeleted = false"
			+ " ORDER BY t.createDate DESC")
	List<TourPost> findByTourStatus();
	
	//ĐỔ CÁC TOUR THEO VÙNG ĐÃ DUYỆT 
	@Query("SELECT t FROM TourPost t WHERE"
			+ " t.status = 'DUYET' AND t.isDeleted = false AND t.toCity.region.regionId = ?1")
	List<TourPost> findByRegion(String rid);
	
	//TÌM KIẾM CÁC TOUR ĐÃ DUYỆT
	@Query("SELECT t FROM TourPost t WHERE"
			+ " t.status = 'DUYET' AND t.isDeleted = false AND"
			+ " CONCAT(t.name, t.type.mainName, t.tranport.name, t.fromCity.name, t.toCity.name, t.toCity.region.name)"
			+ " LIKE %?1%")
	List<TourPost> Search(String keyword);
	
	//LỌC THEO GIÁ TỪ CAO XUỐNG THẤP
	@Query("SELECT t FROM TourPost"
			+ " t WHERE t.status = 'DUYET' AND t.isDeleted = false"
			+ " ORDER BY t.priceTour DESC")
	List<TourPost> filterPriceHigh();
	
	//LỌC THEO GIÁ TỪ THẤP LÊN CAO
	@Query("SELECT t FROM TourPost"
			+ " t WHERE t.status = 'DUYET' AND t.isDeleted = false"
			+ " ORDER BY t.priceTour ASC")
	List<TourPost> filterPriceLow();
	
	//LỌC THEO KHOẢNG GIÁ
	@Query("SELECT t FROM TourPost"
			+ " t WHERE t.status = 'DUYET' AND t.isDeleted = false"
			+ " AND t.priceTour BETWEEN ?1 AND ?2")
	List<TourPost> findAllTourBetweenPrice(Double priceMin, Double priceMax);
	
	////-------------------------------PHẦN DÀNH CHO ADMIN--------------------------------///
	///LẤY CÁC TOUR CHƯA ĐƯỢC DUYỆT
	@Query("SELECT t FROM TourPost t WHERE t.status = 'CHUA' AND t.isDeleted = false "
			+ "ORDER BY t.createDate DESC")
	List<TourPost> findAllByStatusWait();
	
	///LẤY CÁC TOUR ĐÃ DUYỆT HOẶC HỦY
	@Query("SELECT t FROM TourPost t WHERE t.status = 'DUYET' OR t.status = 'HUY' AND t.isDeleted = false "
			+ "ORDER BY t.createDate DESC")
	List<TourPost> findAllByStatusApprove();
	
	//TÌM KIẾM CÁC TOUR
	@Query("SELECT t FROM TourPost t WHERE t.isDeleted = false AND"
			+ " CONCAT(t.tourId, t.name, t.type.mainName, t.tranport.name, t.fromCity.name, t.toCity.name, t.toCity.region.name)"
			+ " LIKE %?1%")
	List<TourPost> SearchAllTour(String keyword);
	
	///------------------------------PHẦN DÀNH CHO DOANH NGHIỆP-----------------------------------////
	///LẤY CÁC TOUR CỦA TÀI KHOẢN ĐÃ ĐĂNG NHẬP
	@Query("SELECT t FROM TourPost t WHERE t.isDeleted = false AND t.account.username = ?1 ORDER BY t.createDate DESC")
	List<TourPost> findAllByUsername(String username);
	
	//TÌM KIẾM CÁC TOUR
	@Query("SELECT t FROM TourPost t"
			+ " WHERE t.account.username = ?1 AND"
			+ " CONCAT(t.tourId, t.name, t.type.mainName, t.tranport.name, t.fromCity.name, t.toCity.name, t.toCity.region.name, t.status)"
			+ " LIKE %?2%")
	List<TourPost> SearchTourByUsername(String username, String keyword);

	@Query("SELECT t FROM TourPost t WHERE t.isDeleted = true")
	List<TourPost> listTrashTour();

	@Query("SELECT t FROM TourPost t WHERE t.isDeleted = true "
			+ "AND CONCAT(t.tourId, t.name, t.type.mainName, t.tranport.name, t.fromCity.name, t.toCity.name, t.toCity.region.name) "
			+ "LIKE %?1%")
	List<TourPost> searchTrashTour(String keyword);

	
}
