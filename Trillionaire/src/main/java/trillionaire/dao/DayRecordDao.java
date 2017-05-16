package trillionaire.dao;

import java.util.List;

import trillionaire.model.DayRecord;

public interface DayRecordDao {
	
	public List<DayRecord> getDayRecordsByCode(int code);

}
