package booking.travel.service;

import java.util.List;

import booking.travel.entity.RatingEvent;

public interface RatingEventService {

	void save(RatingEvent eventRate);

	List<RatingEvent> findAllCommentEventWithUsername(String username);

	RatingEvent findById(String ratingEventId);

	List<RatingEvent> findAllCommentWithEvent(String eventPostId);

}
