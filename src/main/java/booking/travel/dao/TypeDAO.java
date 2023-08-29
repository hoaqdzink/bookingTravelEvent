package booking.travel.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import booking.travel.entity.Type;

public interface TypeDAO extends JpaRepository<Type, String>{
	
}
