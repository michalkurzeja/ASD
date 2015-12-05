package PiApprox;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final int n = (int) Math.pow(10, 8);

        double piSeq, piCon = 0;
        long startSeq, endSeq, startCon, endCon;

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int range = n / availableProcessors;
        int leftOver = n % availableProcessors;
        Collection<PiApprox> approximators = new LinkedList<>();
        List<Future<Double>> futureList;

        for (int i=0; i<availableProcessors; i++) {
            approximators.add(new PiApprox(n, i*range + (i == 0 ? 0 : 1), (i+1)*range + (i == availableProcessors - 1 ? leftOver : 0)));
        }

        ExecutorService pool = Executors.newFixedThreadPool(availableProcessors);

        System.out.println("Sequential approximation...");

        startSeq = System.currentTimeMillis();
        piSeq = new PiApprox(n, 0, n).approximate();
        endSeq = System.currentTimeMillis();

        System.out.println("Pi = " + piSeq + "; Time: " + (endSeq - startSeq) + "ms");

        System.out.println("Concurrent approximation...");
        System.out.println("Running on " + availableProcessors + " threads...");

        startCon = System.currentTimeMillis();
        futureList = pool.invokeAll(approximators);
        endCon = System.currentTimeMillis();

        pool.shutdown();

        for (Future<Double> future : futureList) {
            piCon += future.get();
        }

        System.out.println("Pi = " + piCon + "; Time: " + (endCon - startCon) + "ms");
    }
}
