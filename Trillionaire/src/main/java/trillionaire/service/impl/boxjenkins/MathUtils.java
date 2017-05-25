package trillionaire.service.impl.boxjenkins;

import Jama.Matrix;

/**
 * Created by michaeltan on 2017/5/25.
 */
public class MathUtils {

    /**
     * 平均数
     *
     * @param array 数组
     * @return 平均值
     */
    public double average(double[] array) {
        double result = sum(array) / array.length;
        return result;
    }

    /**
     * 和
     *
     * @param array 数组
     * @return 和
     */
    public double sum(double[] array) {
        double result = 0.00;
        for (int i = 0; i < array.length; i++) {
            result += array[i];
        }
        return result;
    }

    /**
     * 方差
     *
     * @param array 数组
     * @return 方差
     */
    public double variance(double[] array) {
        double variance = 0;
        double average = average(array);

        for (int i = 0; i < array.length; i++) {
            double temp = array[i] - average;
            variance += Math.pow(temp, 2.0);
        }
        return variance / array.length;
    }

    /**
     * 标准差
     *
     * @param array 数组
     * @return 标准差
     */
    public double standardDeviation(double[] array) {
        double variance = variance(array);
        double result = Math.sqrt(variance);
        return result;
    }

    /**
     * 计算自相关系数
     *
     * @param array 数列
     * @param order 阶数
     * @return 自相关系数的数组
     */
    public double[] autoCorrelation(double[] array, int order) {
        double[] autoCor = new double[order + 1];
        double variance = variance(array);

        for (int i = 0; i <= order; i++) {
            autoCor[i] = 0;
            for (int j = 0; j < array.length - i; j++) {
                autoCor[i] += array[j + i] * array[j];
            }
            autoCor[i] /= variance;
        }
        return autoCor;
    }

    /**
     * 计算偏自相关系数
     *
     * @param acfArray 数列
     * @param order    阶数
     * @return 偏自相关系数的数组
     */
    public double[] pacf(double[] acfArray, int order) {
        double[] autoCor = new double[order];
        double[][] matrixData = new double[order][order];
        for (int i = 0; i < order; i++) {
            for (int j = 0; j < i; j++) {
                matrixData[i][j] = acfArray[j];
            }
            matrixData[i][i] = 1;
            for (int j = i + 1; j < order; j++) {
                matrixData[i][j] = acfArray[j - 1];
            }
        }
        Matrix toeplizeMatrix = new Matrix(matrixData);
        Matrix acfMatrix = new Matrix(acfArray, 1);
        Matrix inverseToeplizeMatrix = toeplizeMatrix.inverse();
        Matrix inverseAcfMatrix = acfMatrix.transpose();
        Matrix resultMatrix = inverseToeplizeMatrix.times(inverseAcfMatrix);
        for (int i = 0; i < order; i++) {
            autoCor[i] = resultMatrix.get(i, 0);
        }
        return autoCor;
    }

    /**
     * 计算AR模型的参数p
     *
     * @param array 数组
     * @return 参数p
     */
    public double calculateP(double[] array) {
        double result = 0.00;

        return result;
    }

    /**
     * 计算MA模型的参数q
     *
     * @param array 数组
     * @return 参数q
     */
    public double calculateQ(double[] array) {
        double result = 0.00;

        return result;
    }


}
