package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.TourPostDAO;
import booking.travel.entity.TourPost;
import booking.travel.service.TourPostService;

@Service
public class TourPostServicelmpl implements TourPostService{

	@Autowired
	TourPostDAO tourPostDao;
	
	//ĐỔ CÁC TOUR ĐÃ DUYỆT VÀ ON TOP
	@Override
	public List<TourPost> findByTopPost() {
		return tourPostDao.findByTopPost();
	}

	//ĐỔ CÁC TOUR ĐÃ DUYỆT
	@Override
	public List<TourPost> findByTourStatus() {
		return tourPostDao.findByTourStatus();
	}

	//LỌC CÁC TOUR THEO VÙNG
	@Override
	public List<TourPost> findByRegion(String rid) {
		return tourPostDao.findByRegion(rid);
	}

	//LỌC CÁC TOUR THEO LOẠI
	@Override
	public List<TourPost> findByTypeId(String tid) {
		return tourPostDao.findByTypeId(tid);
	}
	
	@Override
	public List<TourPost> findByTranportId(String trid) {
		return tourPostDao.findByTranportId(trid);
	}

	//XEM CHI TIẾT TOUR
	@Override
	public TourPost findById(String id) {
		return tourPostDao.findById(id).get();
	}

	//TÌM KIẾM TOUR
	@Override
	public List<TourPost> SearchTour(String keyword) {
		if(keyword != null) {
			return tourPostDao.Search(keyword);
		}
		return tourPostDao.findByTourStatus();
	}

	//LỌC GIÁ TỪ CAO ĐẾN THẤP
	@Override
	public List<TourPost> filerPriceHigh() {
		return tourPostDao.filterPriceHigh();
	}

	//LỌC GIÁ TỪ THẤP ĐẾN CAO
	@Override
	public List<TourPost> filerPriceLow() {
		return tourPostDao.filterPriceLow();
	}

	///LỌC THEO KHOẢNG GIÁ
	@Override
	public List<TourPost> filerBetweenPrice(Double priceMin, Double priceMax) {
		return tourPostDao.findAllTourBetweenPrice(priceMin, priceMax);
	}

	///TÌM CÁC TOUR THEO USERNAME ĐĂNG NHẬP CỦA NGƯỜI DÙNG
	@Override
	public List<TourPost> findAllByUsername(String username) {
		return tourPostDao.findAllByUsername(username);
	}
	
	//HÀM LƯU VÀ CẬP NHẬT DÀNH CHO DOANH NGHIỆP
	@Override
	public TourPost save(TourPost tour) {
		tour.setStatus("CHUA");
		return tourPostDao.saveAndFlush(tour);
	}

	@Override
	public void deleteById(String id) {
		tourPostDao.deleteById(id);
		
	}

	/////HÀM TÌM KIẾM TOUR THEO USERNAME
	@Override
	public List<TourPost> searchTourByUsername(String username, String keyword) {
		return tourPostDao.SearchTourByUsername(username, keyword);
	}

	@Override
	public List<TourPost> findAllByStatusWait() {
		return tourPostDao.findAllByStatusWait();
	}

	@Override
	public List<TourPost> findAllByStatusApprove() {
		return tourPostDao.findAllByStatusApprove();
	}

	@Override
	public TourPost saveAdminTour(TourPost tour) {
		return tourPostDao.save(tour);
	}

	@Override
	public List<TourPost> SearchAllTour(String keyword) {
		// TODO Auto-generated method stub
		return tourPostDao.SearchAllTour(keyword);
	}

	@Override
	public void saveIsDeleted(TourPost tourPost) {
		tourPostDao.save(tourPost);
	}

	@Override
	public List<TourPost> listTrashTour() {
		// TODO Auto-generated method stub
		return tourPostDao.listTrashTour();
	}

	@Override
	public List<TourPost> searchTrashTour(String keyword) {
		return tourPostDao.searchTrashTour(keyword);
	}




}
