package booking.travel.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import booking.travel.entity.OrderTour;

public interface OrderTourService {

	List<OrderTour> findAllByUsernameWithStatusWait(String username);
	
	List<OrderTour> findAllByUsernameWithStatusFinal(String username);

	OrderTour findById(String orderTourId);

	void save(OrderTour orderTour);

	List<OrderTour> findOrderByWait(String username);

	List<OrderTour> findOrderByDuyet(String username);

	List<OrderTour> serchOrderTour(String username, String keyword);

	OrderTour create(OrderTour order);

	

}
