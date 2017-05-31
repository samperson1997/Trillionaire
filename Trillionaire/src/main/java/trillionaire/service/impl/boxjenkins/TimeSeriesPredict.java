package trillionaire.service.impl.boxjenkins;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Created by michaeltan on 2017/5/25.
 */
public class TimeSeriesPredict {
    private double predictValue(double[] data) {
        double result;
        ARIMA arima = new ARIMA(data);

        ArrayList<int[]> list = new ArrayList<>();
        int period = 7;
        int modelCnt = 10, cnt = 0; // 通过多次预测的平均值作为预测值
        double[] tmpPredict = new double[modelCnt];
        for (int k = 0; k < modelCnt; ++k) // 控制通过多少组参数进行计算最终的结果
        {
            int[] bestModel = arima.getARIMAModel(period, list, (k == 0) ? false : true);
            if (bestModel.length == 0) {
                tmpPredict[k] = data[data.length - period];
                cnt++;
                break;
            } else {
                double predictDiff = arima.predictValue(bestModel[0], bestModel[1], period);
                tmpPredict[k] = arima.aftDeal(predictDiff, period);
                cnt++;
            }
            list.add(bestModel);
        }
        double sumPredict = 0.00;
        for (int k = 0; k < cnt; ++k) {
            sumPredict += tmpPredict[k] / (double) cnt;
        }
        result = sumPredict;
        double margin = data[data.length - 1] / data[data.length - 2] - 1;
        double correction = 0.00;
        if (Math.abs(margin) >= 0.07) {
            correction = 1 + 10 * Math.sin(Math.abs(margin));
        } else if (Math.abs(margin) >= 0.03 && Math.abs(margin) < 0.07) {
            correction = 1 - 10 * Math.sin(Math.abs(margin));
        } else {
            correction = margin * data[data.length - 1];
        }
        if (margin >= 0) {
            result = result + correction;
        } else {
            result = result - correction;
        }
        return result;
    }

    public double predict(double[] data) {
        double temp;
        double sum = 0.00;
        double result;
        int times = 20;
        for (int i = 0; i < times; i++) {
            temp = predictValue(data);
            sum += temp;
        }
        result = sum / (double) times;
        BigDecimal bigDecimal = new BigDecimal(result);
        return bigDecimal.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}

