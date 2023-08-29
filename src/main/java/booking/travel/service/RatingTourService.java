package booking.travel.service;

import java.util.List;

import booking.travel.entity.RatingTour;

public interface RatingTourService {

	List<RatingTour> findAllCommentWithTour(String id);
	
	void save(RatingTour rateTour);

	List<RatingTour> findAllCommentTourWithUsername(String username);

	RatingTour findById(String ratingTourId);

	List<RatingTour> searchFeedbackTour(String username, String keyword);

	List<RatingTour> findAllByUsernameWithStatusFinal(String username);

}
