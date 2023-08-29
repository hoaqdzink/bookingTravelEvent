package booking.travel.service;

import java.util.List;

import booking.travel.entity.EventPost;

public interface EventPostService {

	List<EventPost> findAllEventByUsername(String username);

	EventPost save(EventPost event);

	EventPost findById(String id);

	void deleteById(String id);

	List<EventPost> searchEventByUsername(String username, String keyword);

	List<EventPost> findAllByStatusWait();

	List<EventPost> findAllByStatusApprove();

	EventPost saveAdminEvent(EventPost event);

	List<EventPost> SearchAllEvent(String keyword);

	void saveIsDeleted(EventPost eventPost);

	List<EventPost> listTrashEvent();

	List<EventPost> findByRegion(String ridE);
	
	List<EventPost> findByEventId(String ev);
	
	List<EventPost> findByTranportId(String tridE);

	List<EventPost> findByEventStatus();

	List<EventPost> filerPriceHigh();

	List<EventPost> filerPriceLow();

	List<EventPost> filerBetweenPrice(Double priceMin, Double priceMax);

	List<EventPost> findByEventTop();

	EventPost detailEvent(String eventPostId);

	List<EventPost> searchTrashEvent(String keyword);

	
}
