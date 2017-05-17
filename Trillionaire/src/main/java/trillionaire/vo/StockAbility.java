package trillionaire.vo;

import trillionaire.util.DecimalUtil;

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

    public String getProfitAbility() {
        String string = DecimalUtil.RemainOneDecimal(profitAbility);
        return string;
    }

    public String getOperationAbility() {
        String string = DecimalUtil.RemainOneDecimal(operationAbility);
        return string;
    }

    public String getGrowthAbility() {
        String string = DecimalUtil.RemainOneDecimal(growthAbility);
        return string;
    }

    public String getDebtPayingAbility() {
        String string = DecimalUtil.RemainOneDecimal(debtPayingAbility);
        return string;
    }
}
