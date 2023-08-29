package booking.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking.travel.entity.RatingEvent;

public interface RatingEventDAO extends JpaRepository<RatingEvent, String>{

	@Query("SELECT rt FROM RatingEvent rt "
			+ "WHERE orderEvent.eventPost.eventPostId=?1 "
			+ "ORDER BY rt.createDate DESC")
	List<RatingEvent> findAllCommentWithEvent(String eventPostId);
	
	///------------------------------PHẦN DÀNH CHO DOANH NGHIỆP-----------------------------------////
	@Query("SELECT rt FROM RatingEvent rt "
			+ "WHERE (rt.orderEvent.eventPost.account.username = ?1) "
			+ "ORDER BY rt.createDate DESC")
	List<RatingEvent> findAllCommentEventWithUsername(String username);
}
