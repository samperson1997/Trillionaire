package trillionaire.dao;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

import trillionaire.model.DayRecord;
import trillionaire.model.MonthRecord;
import trillionaire.model.WeekRecord;

import javax.ejb.Local;

public interface DayRecordDao {
	/**
	 *
	 * @param code
	 * @return
	 */
	public List<DayRecord> getDayRecordsByCode(int code);

	/**
	 *
	 * @param code
	 * @return
	 */
	public List<WeekRecord> getWeekRecordsByCode(int code);

	/**
	 *
	 * @param code
	 * @return
	 */
	public List<MonthRecord> getMonthRecordsByCode(int code);

	/**
	 *
	 * @param dayRecord
	 */
	public void save(DayRecord dayRecord);

	/**
	 *
	 * @param code
	 * @return
	 */
	public Date getLastDateOf(int code);

	/**
	 *
	 * @param weekRecord
	 */
	public void saveWeekRecord(WeekRecord weekRecord);

	/**
	 *
	 * @param code
	 * @return
	 */
	public Date getLastWeekDateOf(int code);

	/**
	 *
	 * @param monthRecord
	 */
	public void saveMonthRecord(MonthRecord monthRecord);

	/**
	 *
	 * @param code
	 * @return
	 */
	public Date getLastMonthDateOf(int code);

	/**
	 *
	 * @param obj
	 */
	public void deletRecord(Object obj);

	/**
	 *
	 * @param code
	 * @param limitNum
	 * @return
	 */
	public List<DayRecord> getDayRecords(int code, int limitNum);

	/**
	 *
	 * @param code1
	 * @param code2
	 * @param limit
	 * @return
	 */
	public List<List<DayRecord>> getAligningDayRecords(int code1, int code2, int limit);

	/**
	 *
	 * @param code
	 * @param date
	 * @param limit
	 * @return
	 */
	public List<DayRecord> getDayRecordBeforeDate(int code, LocalDate date, int limit);

}
