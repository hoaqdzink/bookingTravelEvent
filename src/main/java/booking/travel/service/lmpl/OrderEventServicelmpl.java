package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.OrderEventDAO;
import booking.travel.entity.OrderEvent;
import booking.travel.service.OrderEventService;

@Service
public class OrderEventServicelmpl implements OrderEventService{

	@Autowired
	OrderEventDAO orderEventDao;

	@Override
	public List<OrderEvent> findAllByUsernameWithStatusWait(String username) {
		return orderEventDao.findAllByUsernameWithStatusWait(username);
	}

	@Override
	public List<OrderEvent> findAllByUsernameWithStatusFinal(String username) {
		return orderEventDao.findAllByUsernameWithStatusFinal(username);
	}

	@Override
	public OrderEvent findById(String orderEventId) {
		return orderEventDao.findById(orderEventId).get();
	}

	@Override
	public void save(OrderEvent orderEvent) {
		orderEventDao.save(orderEvent);
	}

	@Override
	public List<OrderEvent> findOrderEventByWait(String username) {
		return orderEventDao.findOrderEventByWait(username);
	}

	@Override
	public List<OrderEvent> findOrderEventByDuyet(String username) {
		return orderEventDao.findOrderEventByDuyet(username);
	}

	@Override
	public List<OrderEvent> serchOrderEvent(String username, String keyword) {
		return orderEventDao.serchOrderEvent(username, keyword);
	}

	@Override
	public OrderEvent create(OrderEvent order) {
		return orderEventDao.save(order);
	}
}
