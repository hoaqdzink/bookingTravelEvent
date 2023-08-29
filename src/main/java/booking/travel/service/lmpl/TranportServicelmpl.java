package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.TranportDAO;
import booking.travel.entity.Tranport;
import booking.travel.service.TranportService;

@Service
public class TranportServicelmpl implements TranportService{

	@Autowired
	TranportDAO tranportDao;
	
	@Override
	public List<Tranport> findAll() {
		return tranportDao.findAll();
	}

	@Override
	public Tranport saveTranport(Tranport tranport) {
		return tranportDao.save(tranport);
	}

	@Override
	public Tranport findById(String tranportId) {
		return tranportDao.findById(tranportId).get();
	}

}
