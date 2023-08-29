package booking.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.travel.entity.Event;

public interface EventDAO extends JpaRepository<Event, String>{

}
