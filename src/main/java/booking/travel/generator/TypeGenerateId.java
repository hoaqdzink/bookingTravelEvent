package booking.travel.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class TypeGenerateId implements IdentifierGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		String prefix = "TYP"; //tiền tố
		Connection connect = session.connection();
		
		try {
			Statement state = connect.createStatement();
			ResultSet rs = state.executeQuery("SELECT MAX(CAST(SUBSTRING(type_id,4,6) AS INT)) FROM type WHERE type_id NOT LIKE '%-%'");
			
			if (rs.next()) 
			{
				int id = rs.getInt(1) + 1;
				@SuppressWarnings("removal")
				String generateId = prefix + new Integer(id).toString();//lấy tiền tố + cho hậu tố là các dãy số tăng dần
				System.out.println("Generated Id: " + generateId);
				return generateId;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
