package trillionaire.dao;

import trillionaire.model.DebtPayingAbility;
import trillionaire.model.DevelopingAbility;
import trillionaire.model.OperationAbility;
import trillionaire.model.Profitability;

/**
 * Created by USER on 2017/5/26.
 */
public interface AbilityDao {

    public void saveProfitability(Profitability profitability);

    public void saveOperationAbility(OperationAbility operationAbility);

    public void saveDevelopingAbility(DevelopingAbility developingAbility);

    public void saveDebtPayingAbility(DebtPayingAbility debtPayingAbility);

    public Profitability getProfitability(int code);

    public OperationAbility getOperationAbility(int code);

    public DevelopingAbility DevelopingAbility(int code);

    public DebtPayingAbility getDebtPayingAbility(int code);

}
