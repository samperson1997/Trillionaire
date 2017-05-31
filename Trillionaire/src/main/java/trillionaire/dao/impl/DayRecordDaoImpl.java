package trillionaire.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import trillionaire.model.DayRecord;
import trillionaire.dao.DayRecordDao;

import javax.annotation.Resource;

@Repository
public class DayRecordDaoImpl implements DayRecordDao{

	@Resource
	SessionFactory sessionFactory;

	public List<DayRecord> getDayRecordsByCode(int code) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Query<DayRecord> query = session.createNativeQuery("select * from day_record where code = "+code+" order by date asc", DayRecord.class);
		List<DayRecord> result = query.list();
		
		tx.commit();
		session.close();
		
		return result;
	}
	
	

}
