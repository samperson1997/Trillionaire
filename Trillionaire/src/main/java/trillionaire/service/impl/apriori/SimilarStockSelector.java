package trillionaire.service.impl.apriori;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by michaeltan on 2017/5/30.
 */
public class SimilarStockSelector {
    private Apriori apriori = new Apriori();

    public List<List<String>> getRecord() {
        List<List<String>> record = new ArrayList<List<String>>();

        /*
        List<String> lineList = new ArrayList<String>();
        for (int i = 0; i < stockList.length; i++) {//处理矩阵中的T、F、YES、NO
            if (){  //涨
                lineList.add(record.get(0).get(i));   //添加股票名称或代码(2选1)
            }
            else if (){  //跌
                ;// F，NO记录不保存
            }
            else{
                lineList.add(stockList[i]);
            }
        }
        record.add(lineList);
        */
        return record;
    }

    public void select(){
        apriori.select();

    }
}
