package trillionaire.service.impl.boxjenkins;

import java.util.Vector;

/**
 * Created by michaeltan on 2017/5/25.
 */
public class AR {
    private double[] data;
    private int p;

    public AR(double[] data, int p) {
        this.data = data;
        this.p = p;
    }

    public Vector<double[]> solveCoeOfAR() {
        Vector<double[]> vec = new Vector<>();
        double[] arCoe = new MathUtil().computeARCoe(this.data, this.p);

        vec.add(arCoe);

        return vec;
    }
}
