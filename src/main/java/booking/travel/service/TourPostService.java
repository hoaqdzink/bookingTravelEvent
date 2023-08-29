package booking.travel.service;

import java.util.List;

import booking.travel.entity.TourPost;

public interface TourPostService {
	
	List<TourPost> findByTopPost();

	List<TourPost> findByTourStatus();

	List<TourPost> findByRegion(String rid);

	List<TourPost> findByTypeId(String tid);

	TourPost findById(String id);

	List<TourPost> SearchTour(String keyword);

	List<TourPost> filerPriceHigh();

	List<TourPost> filerPriceLow();

	List<TourPost> filerBetweenPrice(Double priceMin, Double priceMax);

	List<TourPost> findByTranportId(String trid);

	List<TourPost> findAllByUsername(String username);

	TourPost save(TourPost tour);

	void deleteById(String id);

	List<TourPost> searchTourByUsername(String username, String keyword);

	List<TourPost> findAllByStatusWait();

	List<TourPost> findAllByStatusApprove();

	TourPost saveAdminTour(TourPost tour);

	List<TourPost> SearchAllTour(String keyword);

	void saveIsDeleted(TourPost tourPost);

	List<TourPost> listTrashTour();

	List<TourPost> searchTrashTour(String keyword);

}
