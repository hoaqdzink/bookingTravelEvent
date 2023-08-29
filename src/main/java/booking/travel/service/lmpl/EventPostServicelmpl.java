package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.EventPostDAO;
import booking.travel.entity.EventPost;
import booking.travel.service.EventPostService;

@Service
public class EventPostServicelmpl implements EventPostService{

	@Autowired
	EventPostDAO eventPostDao;

	@Override
	public List<EventPost> findAllEventByUsername(String username) {
		return eventPostDao.findAllEventByUsername(username);
	}

	@Override
	public EventPost save(EventPost event) {
		event.setStatus("CHUA");
		return eventPostDao.saveAndFlush(event);
	}

	@Override
	public EventPost findById(String id) {
		return eventPostDao.findById(id).get();
	}

	@Override
	public void deleteById(String id) {
		eventPostDao.deleteById(id);
		
	}

	@Override
	public List<EventPost> searchEventByUsername(String username, String keyword) {
		return eventPostDao.searchEventByUsername(username, keyword);
	}

	@Override
	public List<EventPost> findAllByStatusWait() {
		return eventPostDao.findAllByStatusWait();
	}

	@Override
	public List<EventPost> findAllByStatusApprove() {
		return eventPostDao.findAllByStatusApprove();
	}

	@Override
	public EventPost saveAdminEvent(EventPost event) {
		return eventPostDao.save(event);
	}

	@Override
	public List<EventPost> SearchAllEvent(String keyword) {
		return eventPostDao.SearchAllEvent(keyword);
	}

	@Override
	public void saveIsDeleted(EventPost eventPost) {
		eventPostDao.save(eventPost);
		
	}

	@Override
	public List<EventPost> listTrashEvent() {
		return eventPostDao.listTrashEvent();
	}

	@Override
	public List<EventPost> findByRegion(String ridE) {
		return eventPostDao.findByRegion(ridE);
	}

	@Override
	public List<EventPost> findByEventId(String ev) {
		return eventPostDao.findByEventId(ev);
	}

	@Override
	public List<EventPost> findByTranportId(String tridE) {
		return eventPostDao.findByTranportId(tridE);
	}

	@Override
	public List<EventPost> findByEventStatus() {
		return eventPostDao.findByEventStatus();
	}

	@Override
	public List<EventPost> filerPriceHigh() {
		return eventPostDao.filerPriceHigh();
	}

	@Override
	public List<EventPost> filerPriceLow() {
		return eventPostDao.filerPriceLow();
	}

	@Override
	public List<EventPost> filerBetweenPrice(Double priceMin, Double priceMax) {
		return eventPostDao.filerBetweenPrice(priceMin, priceMax);
	}

	@Override
	public List<EventPost> findByEventTop() {
		return eventPostDao.findByEventTop();
	}

	@Override
	public EventPost detailEvent(String eventPostId) {
		// TODO Auto-generated method stub
		return eventPostDao.detailEvent(eventPostId);
	}

	@Override
	public List<EventPost> searchTrashEvent(String keyword) {
		return eventPostDao.searchTrashEvent(keyword);
	}

	
}
