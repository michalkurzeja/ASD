package PiApprox;

import java.util.concurrent.Callable;

public class PiApprox implements Callable<Double> {

    private int n;
    private int start;
    private int end;
    private double h;

    public PiApprox(int n, int start, int end) {
        this.n = n;
        this.start = start;
        this.end = end;

        h = 1.0 / n;
    }

    public double approximate() {
        double pi = 0;

        for (int i=start; i<=end; i++) {
            pi += h / (1 + Math.pow(( (i + 0.5) / n ), 2));
        }

        return 4*pi;
    }

    @Override
    public Double call() {
        return approximate();
    }
}
