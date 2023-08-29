package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.EventDAO;
import booking.travel.entity.Event;
import booking.travel.service.EventService;

@Service
public class EventServicelmpl implements EventService{

	@Autowired
	EventDAO eventDao;

	@Override
	public List<Event> findAll() {
		// TODO Auto-generated method stub
		return eventDao.findAll();
	}

	@Override
	public void save(Event event) {
		eventDao.save(event);
		
	}

	@Override
	public Event findById(String eventId) {
		return eventDao.findById(eventId).get();
	}
}
