package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.ToCityDAO;
import booking.travel.entity.ToCity;
import booking.travel.service.ToCityService;

@Service
public class ToCityServicelmpl implements ToCityService{

	@Autowired
	ToCityDAO toCityDao;

	@Override
	public List<ToCity> findAllByRegionId(String regionId) {
		return toCityDao.findAllByRegionId(regionId);
	}

	@Override
	public List<ToCity> findAll() {
		return toCityDao.findAll();
	}

	@Override
	public ToCity findById(String toCityId) {
		return toCityDao.findById(toCityId).get();
	}

	@Override
	public ToCity save(ToCity toCity) {
		return toCityDao.save(toCity);
	}

	@Override
	public void delete(String toCityId) {
		toCityDao.deleteById(toCityId);
		
	}

	@Override
	public List<ToCity> searchToCity(String keyword) {
		return toCityDao.searchToCity(keyword);
	}

}
