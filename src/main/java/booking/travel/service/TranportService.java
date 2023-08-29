package booking.travel.service;

import java.util.List;

import booking.travel.entity.Tranport;

public interface TranportService {

	List<Tranport> findAll();

	Tranport saveTranport(Tranport tranport);

	Tranport findById(String tranportId);

}
