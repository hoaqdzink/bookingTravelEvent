package booking.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking.travel.entity.EventPost;

public interface EventPostDAO extends JpaRepository<EventPost, String>{

	@Query("SELECT e FROM EventPost e "
			+ "WHERE e.status = 'DUYET' AND e.isDeleted = false"
			+ " ORDER BY e.createDate DESC")
	List<EventPost> findByEventStatus();
	
	@Query("SELECT e FROM EventPost e "
			+ "WHERE e.status = 'DUYET' AND e.isDeleted = false AND e.eventPostId = ?1")
	EventPost detailEvent(String eventPostId);
	
	@Query("SELECT e FROM EventPost e "
			+ "WHERE e.status = 'DUYET' AND e.isDeleted = false AND e.toCity.region.regionId = ?1")
	List<EventPost> findByRegion(String ridE);
	
	@Query("SELECT e FROM EventPost e "
			+ "WHERE e.isDeleted = false AND e.status = 'DUYET' AND e.event.eventId = ?1")
	List<EventPost> findByEventId(String ev);

	@Query("SELECT e FROM EventPost e "
			+ "WHERE e.status = 'DUYET' AND e.isDeleted = false AND e.tranport.tranportId = ?1")
	List<EventPost> findByTranportId(String tridE);
	
	@Query("SELECT e FROM EventPost e"
			+ " WHERE e.status = 'DUYET' AND e.isDeleted = false"
			+ " ORDER BY e.priceEvent DESC")
	List<EventPost> filerPriceHigh();

	@Query("SELECT e FROM EventPost e"
			+ " WHERE e.status = 'DUYET' AND e.isDeleted = false"
			+ " ORDER BY e.priceEvent ASC")
	List<EventPost> filerPriceLow();

	@Query("SELECT e FROM EventPost e"
			+ " WHERE e.status = 'DUYET' AND e.isDeleted = false"
			+ " AND e.priceEvent BETWEEN ?1 AND ?2")
	List<EventPost> filerBetweenPrice(Double priceMin, Double priceMax);

	@Query("SELECT e FROM EventPost e "
			+ "WHERE e.status = 'DUYET' AND e.isDeleted = false AND e.topPost = true"
			+ " ORDER BY e.createDate DESC")
	List<EventPost> findByEventTop();
	
	///////------------------PHẦN DÀNH CHO DOANH NGHIỆP------------------------/////////////
	///ĐỔ CÁC EVENT THEO USERNAME
	@Query("SELECT e FROM EventPost e WHERE e.isDeleted = false AND e.account.username = ?1 ORDER BY e.createDate DESC")
	List<EventPost> findAllEventByUsername(String username);
	
	//TÌM KIẾM CÁC EVENT
	@Query("SELECT e FROM EventPost e"
			+ " WHERE e.account.username = ?1 AND"
			+ " CONCAT(e.eventPostId, e.name, e.event.name, e.tranport.name, e.fromCity.name, e.toCity.name, e.toCity.region.name, e.status)"
			+ " LIKE %?2%")
	List<EventPost> searchEventByUsername(String username, String keyword);
	
	@Query("SELECT e FROM EventPost e"
			+ " WHERE e.isDeleted = true AND CONCAT(e.eventPostId, e.name, e.event.name, e.tranport.name, e.fromCity.name, e.toCity.name, e.toCity.region.name, e.status)"
			+ " LIKE %?1%")
	List<EventPost> searchTrashEvent(String keyword);

///////------------------PHẦN DÀNH CHO ADMIN------------------------/////////////
	
	///LẤY CÁC EVENT CHƯA ĐƯỢC DUYỆT
	@Query("SELECT e FROM EventPost e WHERE e.isDeleted = false AND e.status = 'CHUA' "
			+ "ORDER BY e.createDate DESC")
	List<EventPost> findAllByStatusWait();

	///LẤY CÁC EVENT ĐÃ DUYỆT HOẶC HỦY
	@Query("SELECT e FROM EventPost e WHERE e.isDeleted = false AND e.status = 'DUYET' OR e.status = 'HUY' "
			+ "ORDER BY e.createDate DESC")
	List<EventPost> findAllByStatusApprove();

	//TÌM KIẾM CÁC EVENT
	@Query("SELECT e FROM EventPost e WHERE e.isDeleted = false AND"
			+ " CONCAT(e.eventPostId, e.name, e.event.name, e.tranport.name, e.fromCity.name, e.toCity.name, e.toCity.region.name)"
			+ " LIKE %?1%")
	List<EventPost> SearchAllEvent(String keyword);

	@Query("SELECT e FROM EventPost e WHERE e.isDeleted = true")
	List<EventPost> listTrashEvent();
}
