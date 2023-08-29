package booking.travel.service;

import java.util.List;

import booking.travel.entity.Event;

public interface EventService {

	List<Event> findAll();

	void save(Event event);

	Event findById(String eventId);

}
