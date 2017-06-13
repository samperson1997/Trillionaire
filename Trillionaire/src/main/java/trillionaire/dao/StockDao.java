package trillionaire.dao;


import java.util.List;
import java.util.Map;
import java.util.Set;

import trillionaire.model.Stock;

public interface StockDao {

    /**
     *
     * @return
     */
    public List<Stock> getAllStocks();

    /**
     *
     * @param industryName
     * @return
     */
    public Set<Stock> getStocksByIndustry(String industryName);

    /**
     *
     * @param areaName
     * @return
     */
    public Set<Stock> getStocksByArea(String areaName);

    /**
     *
     * @param conceptName
     * @return
     */
    public Set<Stock> getStocksByConcept(String conceptName);

    /**
     *
     * @return
     */
    public Map<String, Object> getNameCodeMap();

}
