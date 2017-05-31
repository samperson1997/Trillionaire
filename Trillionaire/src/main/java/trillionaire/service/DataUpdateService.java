package trillionaire.service;

/**
 * Created by USER on 2017/5/24.
 */
public interface DataUpdateService {

    public void updateAllData();  //调用此方法，将更新数据库的数据至最新交易日

    public void updateAbility(int year, int quarter); //调用次方法向数据库更新该year和quarter的基本面能力数据

}
