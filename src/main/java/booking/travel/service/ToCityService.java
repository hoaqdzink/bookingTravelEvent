package booking.travel.service;

import java.util.List;

import booking.travel.entity.ToCity;

public interface ToCityService {

	List<ToCity> findAllByRegionId(String regionId);

	List<ToCity> findAll();

	ToCity findById(String toCityId);

	ToCity save(ToCity toCity);

	void delete(String toCityId);

	List<ToCity> searchToCity(String keyword);


}
