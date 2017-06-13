package trillionaire.dao;

import trillionaire.model.DebtPayingAbility;
import trillionaire.model.DevelopingAbility;
import trillionaire.model.OperationAbility;
import trillionaire.model.Profitability;

/**
 * Created by USER on 2017/5/26.
 */
public interface AbilityDao {

    /**
     *
     * @param profitability
     */
    public void saveProfitability(Profitability profitability);

    /**
     *
     * @param operationAbility
     */
    public void saveOperationAbility(OperationAbility operationAbility);

    /**
     *
     * @param developingAbility
     */
    public void saveDevelopingAbility(DevelopingAbility developingAbility);

    /**
     *
     * @param debtPayingAbility
     */
    public void saveDebtPayingAbility(DebtPayingAbility debtPayingAbility);

    /**
     *
     * @param code
     * @return
     */
    public Profitability getProfitability(int code);

    /**
     *
     * @param code
     * @return
     */
    public OperationAbility getOperationAbility(int code);

    /**
     *
     * @param code
     * @return
     */
    public DevelopingAbility DevelopingAbility(int code);

    /**
     *
     * @param code
     * @return
     */
    public DebtPayingAbility getDebtPayingAbility(int code);

}
