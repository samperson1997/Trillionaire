package trillionaire.model;

/**
 * Created by michaeltan on 2017/5/14.
 */
public class StockAbility {
    private double profitAbility;  //盈利能力
    private double operationAbility;  //营运能力
    private double growthAbility;  //成长能力
    private double debtPayingAbility;  //偿债能力

    public StockAbility(double profitAbility, double operationAbility, double growthAbility, double debtPayingAbility) {
        this.profitAbility = profitAbility;
        this.operationAbility = operationAbility;
        this.growthAbility = growthAbility;
        this.debtPayingAbility = debtPayingAbility;
    }


}
