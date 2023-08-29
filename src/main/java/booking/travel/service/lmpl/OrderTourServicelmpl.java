package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import booking.travel.dao.OrderTourDAO;
import booking.travel.entity.OrderTour;
import booking.travel.service.OrderTourService;

@Service
public class OrderTourServicelmpl implements OrderTourService{

	@Autowired
	OrderTourDAO orderTourDao;
	
	@Override
	public List<OrderTour> findAllByUsernameWithStatusWait(String username) {
		return orderTourDao.findAllByUsernameWithStatusWait(username);
	}

	@Override
	public List<OrderTour> findAllByUsernameWithStatusFinal(String username) {
		return orderTourDao.findAllByUsernameWithStatusFinal(username);
	}

	@Override
	public OrderTour findById(String orderTourId) {
		return orderTourDao.findById(orderTourId).get();
	}

	@Override
	public void save(OrderTour orderTour) {
		orderTourDao.save(orderTour);
	}

	@Override
	public List<OrderTour> findOrderByWait(String username) {
		return orderTourDao.findOrderWait(username);
	}

	@Override
	public List<OrderTour> findOrderByDuyet(String username) {
		return orderTourDao.findOrderDuyet(username);
	}

	@Override
	public List<OrderTour> serchOrderTour(String username, String keyword) {
		return orderTourDao.searchAllOrderTour(username, keyword);
	}

	@Override
	public OrderTour create(OrderTour order) {
		return orderTourDao.save(order);
	}

//	@Override
//	public OrderTour create(JsonNode orderData) {
//		ObjectMapper mapper = new ObjectMapper();
//		
//		OrderTour order = mapper.convertValue(orderData, OrderTour.class);
//		orderTourDao.save(order);
//		return order;
//	}

	

}
