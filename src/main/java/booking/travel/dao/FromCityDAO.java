package booking.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.travel.entity.FromCity;

public interface FromCityDAO extends JpaRepository<FromCity, String>{

}
