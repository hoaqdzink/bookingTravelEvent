package booking.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking.travel.entity.ToCity;

public interface ToCityDAO extends JpaRepository<ToCity, String>{

	@Query("SELECT t FROM ToCity t WHERE t.region.regionId=?1")
	List<ToCity> findAllByRegionId(String regionId);

	@Query("SELECT t FROM ToCity t WHERE CONCAT(t.name, t.region.name) LIKE %?1%")
	List<ToCity> searchToCity(String keyword);
}
