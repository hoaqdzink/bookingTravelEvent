package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.RegionDAO;
import booking.travel.entity.Region;
import booking.travel.service.RegionService;

@Service
public class RegionServicelmpl implements RegionService{

	@Autowired
	RegionDAO regionDao;
	
	@Override
	public List<Region> findAll() {
		// TODO Auto-generated method stub
		return regionDao.findAll();
	}

}
