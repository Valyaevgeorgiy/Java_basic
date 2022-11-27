package org.pool;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

/*
 A class to handle polynomials: pX=a[0]+a[1]*x+a[2]*x^2.....+a[n]*x^n
*/

public class PolynomialCalculator {
    /*
     * Calculates the value of a polynomial for a given x
     * @param a - an array containing polynomial coefficients
     * @param x Value to substitute in the polynomial
     * @return The value of the polynomial for a given x
    */

    public static double polinomialCalc(double [] a, double x) {
        double pX = 0;
        for (int j = 0; j < a.length; j++) {
            pX += a[j] * Math.pow(x, j);
        }
        return pX;
    }
}

class VectorPolynomialCalculator implements Runnable {

    private CountDownLatch latch;
    private final double[] a;
    private final double[] x;
    private final double[] pX;
    private final int start;
    private final int end;

    /*
     *
     * @param a Constructor
     * @param x
     * @param pX
     * @param start
     * @param end
    */

    VectorPolynomialCalculator(double[] a, double[] x, double[] pX, int start, int end, CountDownLatch latch) {
        this.a = a;
        this.x = x;
        this.pX = pX;
        this.start = start;
        this.end = end;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            pX[i] = PolynomialCalculator.polinomialCalc(a, x[i]);
        }
        latch.countDown();
    }
}

class ThreadPoolExamples {
    public static final int sizeOfArray = 40000000;
    public static final int batch = 2000000;
    public static double [] x = new double[sizeOfArray];
    public static double [] pX = new double[sizeOfArray];

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Random r = new Random();

        // инициализация
        for (int i = 0; i < sizeOfArray; i++) {
            x[i] = r.nextDouble();
        }

        double[] a = new double[20];

        // инициализация
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt();
        }

        long startTime, stopTime;

        System.out.println("Size array = " + sizeOfArray);

        // Решение без применения многопоточности
        startTime = System.nanoTime();
        for (int i = 0; i < sizeOfArray; i++) {
            pX[i] = PolynomialCalculator.polinomialCalc(a, x[i]);
        }
        stopTime = System.nanoTime();
        System.out.println("elapsed time: " + ( (stopTime - startTime) / 1000 ) + " microseconds.");

        startTime = System.nanoTime();

        // Решение с применением многопоточности

        CountDownLatch latch1 = new CountDownLatch(sizeOfArray / batch);
        ExecutorService executorService = Executors.newFixedThreadPool(sizeOfArray / batch);
        for (int i = 0; i < (sizeOfArray / batch); i++) {
            executorService.submit(new VectorPolynomialCalculator(a, x,
                    pX, i * batch, (i + 1) * batch - 1, latch1));
        }

        latch1.await();
        stopTime = System.nanoTime();
        System.out.println("elapsed time: " + ( (stopTime - startTime) / 1000) + " microseconds.");

        /*                       YOUR TASK
        Please add your code wherever you find a comment with todo comment
        The ultimate goal is to find the sum of all elements in array "pX"
        */

        double total = 0;
        startTime = System.nanoTime();

        //TODO: calculate the sum of all elements in in array output using "for"
        //......................
        for (int i = 0; pX.length > i; i++) {
            total += pX[i];
        }

        stopTime = System.nanoTime();
        //TODO: calculate execution time and output it in console
        //......................

        System.out.println();
        System.out.printf("Total Summa: %s%ntime: %s microseconds%n", total, (stopTime - startTime) / 1000);
        System.out.println();

        total = 0;
        startTime = System.nanoTime();
        ArrayList<Future<Double>> list = new ArrayList<>();
        for (int i = 0; i < (sizeOfArray / batch); i++) {
            //TODO: create threads using interface Callable
            //Hint: Callable<Double> callable=........

            Callable<Double> callable = new ArraySum(pX, i * batch, (i + 1) * batch - 1);

            //TODO: create Future<Double> objects to store results asynchronously
            //Hint: Future<Double> future=........

            Future<Double> future = executorService.submit(callable);

            //TODO: store results in the list
            //.................

            list.add(future);
        }

        //calculate final result
        for (Future<Double> future : list) {
            //TODO: calculate final result.
            //total += ....

            total += future.get();
        }

        stopTime = System.nanoTime();
        //TODO: calculate execution time and output it in console
        //......................
        System.out.printf("Total Summa: %s%ntime: %s microseconds", total, (stopTime - startTime) / 1000);
    }
}

class ArraySum implements Callable<Double> {
    private final double[] array;
    private final int start, end;

    //TODO: add an appropriate constructor
    //..............................

    ArraySum(double[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    //TODO: override function call
    //..............................

    @Override
    public Double call() throws Exception {
        double pX = 0;
        for (int j = start; j <= end; j++) {
            pX += array[j];
        }
        return pX;
    }
}
