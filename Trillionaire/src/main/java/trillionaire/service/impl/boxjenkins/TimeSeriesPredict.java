package trillionaire.service.impl.boxjenkins;

import java.util.ArrayList;

/**
 * Created by michaeltan on 2017/5/25.
 */
public class TimeSeriesPredict {
    public double predict(double[] data) {
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
        result = sumPredict + Math.sin(data[data.length - 1] / data[data.length - 2] - 1);
        return result;
    }

}

