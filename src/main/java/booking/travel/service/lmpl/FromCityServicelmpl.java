package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.FromCityDAO;
import booking.travel.entity.FromCity;
import booking.travel.service.FromCityService;

@Service
public class FromCityServicelmpl implements FromCityService{

	@Autowired
	FromCityDAO fromCityDao;

	@Override
	public List<FromCity> findAll() {
		// TODO Auto-generated method stub
		return fromCityDao.findAll();
	}
}
