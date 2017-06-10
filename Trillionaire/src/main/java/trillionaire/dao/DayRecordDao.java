package trillionaire.dao;

import java.time.LocalDate;
import java.util.List;
import java.sql.Date;

import trillionaire.model.DayRecord;
import trillionaire.model.MonthRecord;
import trillionaire.model.WeekRecord;

import javax.ejb.Local;

public interface DayRecordDao {
	
	public List<DayRecord> getDayRecordsByCode(int code);

	public List<WeekRecord> getWeekRecordsByCode(int code);

	public List<MonthRecord> getMonthRecordsByCode(int code);

	public void save(DayRecord dayRecord);

	public Date getLastDateOf(int code);

	public void saveWeekRecord(WeekRecord weekRecord);

	public Date getLastWeekDateOf(int code);

	public void saveMonthRecord(MonthRecord monthRecord);

	public Date getLastMonthDateOf(int code);

	public void deletRecord(Object obj);

	public List<DayRecord> getDayRecords(int code, int limitNum);

	public List<List<DayRecord>> getAligningDayRecords(int code1, int code2, int limit);

	public List<DayRecord> getDayRecordBeforeDate(int code, LocalDate date, int limit);

}
