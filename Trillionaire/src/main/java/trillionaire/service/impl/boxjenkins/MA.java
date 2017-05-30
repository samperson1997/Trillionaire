package trillionaire.service.impl.boxjenkins;

import java.util.Vector;

/**
 * Created by michaeltan on 2017/5/25.
 */
public class MA {
    private double[] data;
    private int q;

    public MA(double[] data, int q) {
        this.data = data;
        this.q = q;
    }

    public Vector<double[]> solveCoeOfMA() {
        Vector<double[]> vec = new Vector<>();
        double[] maCoe = new MathUtil().computeMACoe(this.data, this.q);

        vec.add(maCoe);

        return vec;
    }
}