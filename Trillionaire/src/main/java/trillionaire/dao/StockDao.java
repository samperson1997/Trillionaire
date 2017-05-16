package trillionaire.dao;


import java.util.List;
import java.util.Set;

import trillionaire.model.Stock;

public interface StockDao {

    public List<Stock> getAllStocks();

    public Set<Stock> getStocksByIndustry(String industryName);

    public Set<Stock> getStocksByArea(String areaName);

    public Set<Stock> getStocksByConcept(String conceptName);

}
