package trillionaire.dao;

import java.util.Map;

/**
 * Created by USER on 2017/6/12.
 */
public interface ConceptDao {

    public Map<Integer, String> getAllConcepts();

    public Map<Integer, String> getAllIndustry();

    public Map<Integer, String> getAllArea();

}
