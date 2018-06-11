package trillionaire.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trillionaire.model.DayRecord;
import trillionaire.dao.DayRecordDao;
import trillionaire.model.MonthRecord;
import trillionaire.model.WeekRecord;

import javax.annotation.Resource;

@Repository
@Transactional
public class DayRecordDaoImpl implements DayRecordDao{

	@Autowired
	SessionFactory sessionFactory;

	public List<DayRecord> getDayRecordsByCode(int code) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		
		Query<DayRecord> query = session.createNativeQuery("select * from day_record where code = "+code+" order by date asc", DayRecord.class);
		List<DayRecord> result = query.list();
		
//		tx.commit();
//		session.close();
		
		return result;
	}

	public List<WeekRecord> getWeekRecordsByCode(int code) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();

		Query<WeekRecord> query = session.createNativeQuery("select * from week_record where code = "+code+" order by date asc", WeekRecord.class);
		List<WeekRecord> result = query.list();

//		tx.commit();
//		session.close();

		return result;
	}

	public List<MonthRecord> getMonthRecordsByCode(int code) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();

		Query<MonthRecord> query = session.createNativeQuery("select * from month_record where code = "+code+" order by date asc", MonthRecord.class);
		List<MonthRecord> result = query.list();

//		tx.commit();
//		session.close();

		return result;
	}

	public void save(DayRecord dayRecord) {

//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		dayRecord.setDate(Date.valueOf(dayRecord.getDate().toLocalDate()));
		session.saveOrUpdate(dayRecord);

//		tx.commit();
//		session.close();

	}

	public Date getLastDateOf(int code) {

//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery("select max(date) from day_record where code = "+code);
		Date result = (Date)query.uniqueResult();

//		tx.commit();
//		session.close();

		return result;
	}

	public void saveWeekRecord(WeekRecord weekRecord) {

//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		weekRecord.setDate(Date.valueOf(weekRecord.getDate().toLocalDate()));
		session.saveOrUpdate(weekRecord);

//		tx.commit();
//		session.close();

	}

	public Date getLastWeekDateOf(int code) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery("select max(date) from week_record where code = "+code);
		Date result = (Date)query.uniqueResult();

//		tx.commit();
//		session.close();

		return result;
	}

	public void saveMonthRecord(MonthRecord monthRecord) {

//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		monthRecord.setDate(Date.valueOf(monthRecord.getDate().toLocalDate()));
		session.saveOrUpdate(monthRecord);

//		tx.commit();
//		session.close();

	}

	public Date getLastMonthDateOf(int code) {
//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery("select max(date) from month_record where code = "+code);
		Date result = (Date)query.uniqueResult();

//		tx.commit();
//		session.close();

		return result;
	}

	@Override
	public void deletRecord(Object obj) {

//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		session.delete(obj);

//		tx.commit();
//		session.close();

	}

	@Override
	public List<DayRecord> getDayRecords(int code, int limitNum) {


//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		Query<DayRecord> query = session.createNativeQuery("select * from day_record where code = " +  code + " order by date desc limit " + limitNum, DayRecord.class);
		List<DayRecord> result = query.getResultList();


//		tx.commit();
//		session.close();
		return result;

	}

	@Override
	public List<List<DayRecord>> getAligningDayRecords(int code1, int code2, int limit) {


//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();
		Session session = sessionFactory.getCurrentSession();
		Query<DayRecord> query1 = session.createNativeQuery("SELECT d1.* from day_record d1 INNER JOIN day_record d2 " +
				"					on d1.date = d2.date WhERE d1.code = " +code1+" and d2.code = "+code2+" ORDER BY date DESC LIMIT " + limit, DayRecord.class);
		List<DayRecord> result1 = query1.getResultList();

		Query<DayRecord> query2 = session.createNativeQuery("SELECT d2.* from day_record d1 INNER JOIN day_record d2 " +
				"					on d1.date = d2.date WhERE d1.code = " +code1+" and d2.code = "+code2+" ORDER BY date DESC LIMIT " + limit, DayRecord.class);
		List<DayRecord> result2 = query2.getResultList();

		List<List<DayRecord>> result = new ArrayList<>();
		result.add(result1);
		result.add(result2);
//
//		tx.commit();
//		session.close();

		return result;
	}

	@Override
	public List<DayRecord> getDayRecordBeforeDate(int code, LocalDate date, int limit) {

//		Session session = sessionFactory.openSession();
//		Transaction tx = session.beginTransaction();

		Session session = sessionFactory.getCurrentSession();

		Query<DayRecord> query = session.createNativeQuery("SELECT * from day_record WHERE code = "+code+" and date < '"+date.toString()+"' ORDER BY date DESC LIMIT "+limit, DayRecord.class);
		List<DayRecord> result = query.getResultList();


//		tx.commit();
//		session.close();
		return result;
	}


}
