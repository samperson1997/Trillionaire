package trillionaire.dao;

import java.util.List;
import java.sql.Date;

import trillionaire.model.DayRecord;
import trillionaire.model.MonthRecord;
import trillionaire.model.WeekRecord;

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

}
