package booking.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.travel.entity.Tranport;

public interface TranportDAO extends JpaRepository<Tranport, String>{

}
