package trillionaire.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import trillionaire.model.DayRecord;
import trillionaire.model.Stock;
import trillionaire.dao.DayRecordDao;

public class DayRecordDaoImpl implements DayRecordDao{
	
	SessionFactory sessionFactory = null;

	public List<DayRecord> getDayRecordsByCode(int code) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query<DayRecord> query = session.createNativeQuery("select * from day_record where code = 2 order by date asc", DayRecord.class);
		List<DayRecord> result = query.list();
		
		tx.commit();
		session.close();
		
		return result;
	}
	
	

}
