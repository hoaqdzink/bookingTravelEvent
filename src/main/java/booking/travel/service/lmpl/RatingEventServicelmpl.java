package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.RatingEventDAO;
import booking.travel.entity.RatingEvent;
import booking.travel.service.RatingEventService;

@Service
public class RatingEventServicelmpl implements RatingEventService{

	@Autowired
	RatingEventDAO ratingEventDao;

	@Override
	public void save(RatingEvent eventRate) {
		ratingEventDao.save(eventRate);	
	}

	@Override
	public List<RatingEvent> findAllCommentEventWithUsername(String username) {
		return ratingEventDao.findAllCommentEventWithUsername(username);
	}

	@Override
	public RatingEvent findById(String ratingEventId) {
		return ratingEventDao.findById(ratingEventId).get();
	}

	@Override
	public List<RatingEvent> findAllCommentWithEvent(String eventPostId) {
		return ratingEventDao.findAllCommentWithEvent(eventPostId);
	}
}
