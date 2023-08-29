package booking.travel.service.lmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import booking.travel.dao.TypeDAO;
import booking.travel.entity.Type;
import booking.travel.service.TypeService;

@Service
public class TypeServicelmpl implements TypeService{

	@Autowired
	TypeDAO typeDao;

	@Override
	public List<Type> findAll() {
		// TODO Auto-generated method stub
		return typeDao.findAll();
	}

	@Override
	public void saveType(Type type) {
		typeDao.saveAndFlush(type);
	}

	@Override
	public Type findById(String typeId) {
		// TODO Auto-generated method stub
		return typeDao.findById(typeId).get();
	}

	@Override
	public Type save(Type type) {
		// TODO Auto-generated method stub
		return typeDao.save(type);
	}

}
