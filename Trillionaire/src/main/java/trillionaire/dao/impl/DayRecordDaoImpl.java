package trillionaire.dao.impl;

import java.sql.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.springframework.stereotype.Repository;
import trillionaire.model.DayRecord;
import trillionaire.dao.DayRecordDao;
import trillionaire.model.MonthRecord;
import trillionaire.model.WeekRecord;

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

	public List<WeekRecord> getWeekRecordsByCode(int code) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query<WeekRecord> query = session.createNativeQuery("select * from week_record where code = "+code+" order by date asc", WeekRecord.class);
		List<WeekRecord> result = query.list();

		tx.commit();
		session.close();

		return result;
	}

	public List<MonthRecord> getMonthRecordsByCode(int code) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query<MonthRecord> query = session.createNativeQuery("select * from month_record where code = "+code+" order by date asc", MonthRecord.class);
		List<MonthRecord> result = query.list();

		tx.commit();
		session.close();

		return result;
	}

	public void save(DayRecord dayRecord) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		dayRecord.setDate(Date.valueOf(dayRecord.getDate().toLocalDate()));
		session.saveOrUpdate(dayRecord);

		tx.commit();
		session.close();

	}

	public Date getLastDateOf(int code) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createNativeQuery("select max(date) from day_record where code = "+code);
		Date result = (Date)query.uniqueResult();

		tx.commit();
		session.close();

		return result;
	}

	public void saveWeekRecord(WeekRecord weekRecord) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		weekRecord.setDate(Date.valueOf(weekRecord.getDate().toLocalDate()));
		session.saveOrUpdate(weekRecord);

		tx.commit();
		session.close();

	}

	public Date getLastWeekDateOf(int code) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createNativeQuery("select max(date) from week_record where code = "+code);
		Date result = (Date)query.uniqueResult();

		tx.commit();
		session.close();

		return result;
	}

	public void saveMonthRecord(MonthRecord monthRecord) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		monthRecord.setDate(Date.valueOf(monthRecord.getDate().toLocalDate()));
		session.saveOrUpdate(monthRecord);

		tx.commit();
		session.close();

	}

	public Date getLastMonthDateOf(int code) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query query = session.createNativeQuery("select max(date) from month_record where code = "+code);
		Date result = (Date)query.uniqueResult();

		tx.commit();
		session.close();

		return result;
	}

	@Override
	public void deletRecord(Object obj) {

		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.delete(obj);

		tx.commit();
		session.close();

	}

	@Override
	public List<DayRecord> getDayRecords(int code, int limitNum) {


		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Query<DayRecord> query = session.createNativeQuery("select * from day_record where code = " +  code + " order by date desc limit " + limitNum);
		List<DayRecord> result = query.getResultList();


		tx.commit();
		session.close();
		return result;

	}


}
