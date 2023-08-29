package booking.travel.service;

import java.util.List;

import booking.travel.entity.Type;


public interface TypeService {

	List<Type> findAll();

	void saveType(Type type);

	Type findById(String typeId);

	Type save(Type type);

}
