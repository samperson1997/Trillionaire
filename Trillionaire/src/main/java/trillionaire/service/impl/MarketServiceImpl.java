package trillionaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trillionaire.dao.ConceptDao;
import trillionaire.dao.DayRecordDao;
import trillionaire.dao.RealTimeStockDao;
import trillionaire.dao.StockDao;
import trillionaire.model.DayRecord;
import trillionaire.model.RealTimeStock;
import trillionaire.model.Stock;
import trillionaire.service.MarketService;
import trillionaire.vo.BoardVO;
import trillionaire.vo.RankTable;

import java.text.Collator;
import java.util.*;

/**
 * Created by michaeltan on 2017/5/16.
 */
@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    RealTimeStockDao realTimeStockDao;
    @Autowired
    StockDao stockDao;
    @Autowired
    DayRecordDao dayRecordDao;
    @Autowired
    ConceptDao conceptDao;

    @Override
    public Map<String, Object> getSquare(String board) {
        List<RankTable> upList = new ArrayList<>();
        List<RankTable> downList = new ArrayList<>();
        Map<Integer, String> map;
        Set<Stock> stockList;
        if (board.equals("industry")) {
            map = conceptDao.getAllIndustry();
        } else if (board.equals("area")) {
            map = conceptDao.getAllArea();
        } else if (board.equals("concept")) {
            map = conceptDao.getAllConcepts();
        } else {
            return null;
        }
        for (Integer o : map.keySet()) {
            String name = map.get(o);
            if (board.equals("industry")) {
                stockList = stockDao.getStocksByIndustry(name);
            } else if (board.equals("area")) {
                stockList = stockDao.getStocksByArea(name);
            } else if (board.equals("concept")) {
                stockList = stockDao.getStocksByConcept(name);
            } else {
                continue;
            }
            Iterator<Stock> it = stockList.iterator();
            List<Integer> codeList = new ArrayList<>();
            while (it.hasNext()) {
                codeList.add(it.next().getCode());
            }
            List<DayRecord> newRecord = new ArrayList<>();
            for (int i = 0; i < codeList.size(); i++) {
                List<DayRecord> recordList = dayRecordDao.getDayRecordsByCode(codeList.get(i));
                if (recordList.size() >= 2 && recordList != null) {
                    newRecord.add(recordList.get(recordList.size() - 1));
                }
            }
            if (newRecord.size() != 0) {
                int up = 0;
                int remain = 0;
                int down = 0;
                double margin = 0.00;
                for (int i = 0; i < newRecord.size(); i++) {
                    margin += newRecord.get(i).getChange();
                    if (newRecord.get(i).getChange() > 0) {
                        up++;
                    } else if (newRecord.get(i).getChange() < 0) {
                        down++;
                    } else {
                        remain++;
                    }
                }
                margin = margin / newRecord.size();
                Collections.sort(newRecord, new Comparator<DayRecord>() {
                    @Override
                    public int compare(DayRecord o1, DayRecord o2) {
                        return new Double(o2.getChange()).compareTo(o1.getChange());
                    }
                });
                RankTable rankTable = new RankTable(name, margin, up, remain, down, newRecord.get(0).getStock().getName(), newRecord.get(0).getChange());
                if (margin > 0) {
                    upList.add(rankTable);
                } else if (margin < 0) {
                    downList.add(rankTable);
                }
            }
        }
        Collections.sort(upList, new Comparator<RankTable>() {
            @Override
            public int compare(RankTable o1, RankTable o2) {
                return new Double(o2.getMargin().substring(0, o2.getMargin().length() - 1)).compareTo(Double.valueOf(o1.getMargin().substring(0, o1.getMargin().length() - 1)));
            }
        });
        Collections.sort(downList, new Comparator<RankTable>() {
            @Override
            public int compare(RankTable o1, RankTable o2) {
                return new Double(o1.getMargin().substring(0, o1.getMargin().length() - 1)).compareTo(Double.valueOf(o2.getMargin().substring(0, o2.getMargin().length() - 1)));
            }
        });

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("up", upList);
        resultMap.put("down", downList);
        return resultMap;
    }

    @Override
    public Map<String, Object> getBoardRank() {
        Map<String, Object> map = new HashMap<>();
        List<RealTimeStock> list = realTimeStockDao.getAll();
        List<RealTimeStock> ss = new ArrayList<>();
        List<RealTimeStock> sz = new ArrayList<>();
        List<RealTimeStock> gem = new ArrayList<>();
        List<RealTimeStock> sme = new ArrayList<>();
        Collections.sort(list, new Comparator<RealTimeStock>() {
            @Override
            public int compare(RealTimeStock o1, RealTimeStock o2) {
                return new Double(o2.getChangepercent()).compareTo(o1.getChangepercent());
            }
        });

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCode().startsWith("6")) { //沪A
                ss.add(list.get(i));
            } else if (list.get(i).getCode().startsWith("000")) { //深A
                sz.add(list.get(i));
            } else if (list.get(i).getCode().startsWith("3")) {  //创业板
                gem.add(list.get(i));
            } else if (list.get(i).getCode().startsWith("002")) {  //中小板
                sme.add(list.get(i));
            }
        }

        Collections.sort(list, new Comparator<RealTimeStock>() {
            @Override
            public int compare(RealTimeStock o1, RealTimeStock o2) {
                return new Double(o1.getChangepercent()).compareTo(o2.getChangepercent());
            }
        });

        map.put("ss", ss);
        map.put("sz", sz);
        map.put("gem", gem);
        map.put("sme", sme);
        return map;
    }

    @Override
    public List<RealTimeStock> getIndustryRank(String industry) {
        Set<Stock> list = stockDao.getStocksByIndustry(industry);
        Iterator<Stock> it = list.iterator();
        List<Integer> codeList = new ArrayList<>();
        List<RealTimeStock> resultList = new ArrayList<>();
        while (it.hasNext()) {
            codeList.add(it.next().getCode());
        }
        for (int i = 0; i < codeList.size(); i++) {
            RealTimeStock realTimeStock = realTimeStockDao.getRealTimeByCode(codeList.get(i));
            if (realTimeStock != null) {
                resultList.add(realTimeStock);
            }
        }
        Collections.sort(resultList, new Comparator<RealTimeStock>() {
            @Override
            public int compare(RealTimeStock o1, RealTimeStock o2) {
                return new Double(o2.getChangepercent()).compareTo(o1.getChangepercent());
            }
        });
        if (resultList.size() != 0) {
            return resultList;
        } else {
            return null;
        }
    }

    @Override
    public List<RealTimeStock> getConceptRank(String concept) {
        Set<Stock> list = stockDao.getStocksByConcept(concept);
        Iterator<Stock> it = list.iterator();
        List<Integer> codeList = new ArrayList<>();
        List<RealTimeStock> resultList = new ArrayList<>();
        while (it.hasNext()) {
            codeList.add(it.next().getCode());
        }
        for (int i = 0; i < codeList.size(); i++) {
            RealTimeStock realTimeStock = realTimeStockDao.getRealTimeByCode(codeList.get(i));
            if (realTimeStock != null) {
                resultList.add(realTimeStock);
            }
        }
        Collections.sort(resultList, new Comparator<RealTimeStock>() {
            @Override
            public int compare(RealTimeStock o1, RealTimeStock o2) {
                return new Double(o2.getChangepercent()).compareTo(o1.getChangepercent());
            }
        });

        if (resultList.size() != 0) {
            return resultList;
        } else {
            return null;
        }
    }

    @Override
    public List<RealTimeStock> getAreaRank(String area) {
        Set<Stock> list = stockDao.getStocksByArea(area);
        Iterator<Stock> it = list.iterator();
        List<Integer> codeList = new ArrayList<>();
        List<RealTimeStock> resultList = new ArrayList<>();
        while (it.hasNext()) {
            codeList.add(it.next().getCode());
        }
        for (int i = 0; i < codeList.size(); i++) {
            RealTimeStock realTimeStock = realTimeStockDao.getRealTimeByCode(codeList.get(i));
            if (realTimeStock != null) {
                resultList.add(realTimeStock);
            }
        }
        Collections.sort(resultList, new Comparator<RealTimeStock>() {
            @Override
            public int compare(RealTimeStock o1, RealTimeStock o2) {
                return new Double(o2.getChangepercent()).compareTo(o1.getChangepercent());
            }
        });

        if (resultList.size() != 0) {
            return resultList;
        } else {
            return null;
        }
    }

    @Override
    public List<BoardVO> getAreaList() {
        Map<Integer, String> map = conceptDao.getAllArea();
        List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());
        List<BoardVO> result = new ArrayList<>();
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
            //升序排序
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                Collator collator = Collator.getInstance(Locale.CHINA);
                return collator.compare(o1.getValue(), o2.getValue());
            }
        });
        Iterator<Map.Entry<Integer, String>> iter = list.iterator();
        Map.Entry<Integer, String> tmpEntry;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            result.add(new BoardVO(tmpEntry.getKey(), tmpEntry.getValue()));
        }
        return result;
    }

    @Override
    public List<BoardVO> getConceptList() {
        Map<Integer, String> map = conceptDao.getAllConcepts();
        List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());
        List<BoardVO> result = new ArrayList<>();
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
            //升序排序
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                Collator collator = Collator.getInstance(Locale.CHINA);
                return collator.compare(o1.getValue(), o2.getValue());
            }
        });
        Iterator<Map.Entry<Integer, String>> iter = list.iterator();
        Map.Entry<Integer, String> tmpEntry;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            result.add(new BoardVO(tmpEntry.getKey(), tmpEntry.getValue()));
        }
        return result;
    }

    @Override
    public List<BoardVO> getIndustryList() {
        Map<Integer, String> map = conceptDao.getAllIndustry();
        List<Map.Entry<Integer, String>> list = new ArrayList<>(map.entrySet());
        List<BoardVO> result = new ArrayList<>();
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
            //升序排序
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                Collator collator = Collator.getInstance(Locale.CHINA);
                return collator.compare(o1.getValue(), o2.getValue());
            }
        });
        Iterator<Map.Entry<Integer, String>> iter = list.iterator();
        Map.Entry<Integer, String> tmpEntry;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            result.add(new BoardVO(tmpEntry.getKey(), tmpEntry.getValue()));
        }
        return result;
    }
}
