package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.RatingTourDAO;
import booking.travel.entity.RatingTour;
import booking.travel.service.RatingTourService;

@Service
public class RatingTourServicelmpl implements RatingTourService{

	@Autowired
	RatingTourDAO ratingTourDao;
	
	@Override
	public List<RatingTour> findAllCommentWithTour(String id) {
		return ratingTourDao.findAllCommentWithTour(id);
	}

	@Override
	public void save(RatingTour rateTour) {
		ratingTourDao.save(rateTour);
		
	}

	@Override
	public List<RatingTour> findAllCommentTourWithUsername(String username) {
		return ratingTourDao.findAllCommentTourWithUsername(username);
	}

	@Override
	public RatingTour findById(String ratingTourId) {
		return ratingTourDao.findById(ratingTourId).get();
	}

	@Override
	public List<RatingTour> searchFeedbackTour(String username, String keyword) {
		// TODO Auto-generated method stub
		return ratingTourDao.searchFeedbackTour(username, keyword);
	}

	@Override
	public List<RatingTour> findAllByUsernameWithStatusFinal(String username) {
		return ratingTourDao.findAllByUsernameWithStatusFinal(username);
	}
}
