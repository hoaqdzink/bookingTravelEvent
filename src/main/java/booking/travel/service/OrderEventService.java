package booking.travel.service;

import java.util.List;

import booking.travel.entity.OrderEvent;

public interface OrderEventService {

	List<OrderEvent> findAllByUsernameWithStatusWait(String username);
	
	List<OrderEvent> findAllByUsernameWithStatusFinal(String username);

	OrderEvent findById(String orderEventId);

	void save(OrderEvent orderEvent);

	List<OrderEvent> findOrderEventByWait(String username);

	List<OrderEvent> findOrderEventByDuyet(String username);

	List<OrderEvent> serchOrderEvent(String username, String keyword);

	OrderEvent create(OrderEvent order);

	

}
