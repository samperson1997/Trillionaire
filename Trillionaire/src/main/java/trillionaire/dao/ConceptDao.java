package trillionaire.dao;

import java.util.Map;

/**
 * Created by USER on 2017/6/12.
 */
public interface ConceptDao {

    /**
     *
     * @return
     */
    public Map<Integer, String> getAllConcepts();

    /**
     *
     * @return
     */
    public Map<Integer, String> getAllIndustry();

    /**
     *
     * @return
     */
    public Map<Integer, String> getAllArea();

}
