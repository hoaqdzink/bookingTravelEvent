package booking.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import booking.travel.entity.RatingTour;

public interface RatingTourDAO extends JpaRepository<RatingTour, String>{

	@Query("SELECT rt FROM RatingTour rt WHERE orderTour.tourPost.tourId=?1 ORDER BY rt.createDate DESC")
	List<RatingTour> findAllCommentWithTour(String tourId);
	
	///------------------------------PHẦN DÀNH CHO DOANH NGHIỆP-----------------------------------////
	@Query("SELECT rt FROM RatingTour rt WHERE (rt.orderTour.tourPost.account.username = ?1) ORDER BY rt.createDate DESC")
	List<RatingTour> findAllCommentTourWithUsername(String username);
	
	@Query("SELECT rt FROM RatingTour rt "
			+ "WHERE (rt.orderTour.tourPost.account.username = ?1) "
			+ "AND CONCAT(rt.orderTour.account.username, rt.orderTour.account.fullname, rt.orderTour.account.phone, rt.comment) "
			+ "LIKE %?2%")
	List<RatingTour> searchFeedbackTour(String username, String keyword);

	@Query("SELECT rt FROM RatingTour rt "
			+ "WHERE rt.orderTour.status = 'DUYET' AND rt.orderTour.account.username = ?1 "
			+ "ORDER BY rt.orderTour.createDate DESC")
	List<RatingTour> findAllByUsernameWithStatusFinal(String username);
}
