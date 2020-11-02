package edu.dsu.zehret.csc482;

import java.util.ArrayList;
import java.util.Random;
import java.math.BigInteger;

public class Main {


    private static ArrayList<Result> results = new ArrayList<Result>();

    private static final int MAX_FIB_X = 50;

    public static void main(String[] args) throws InterruptedException {
        
        Random r = new Random();
        for(int i = 0; i < 20; i++) {
            BigInteger x = new BigInteger(r.nextInt(50000) + "");
            BigInteger y = new BigInteger(r.nextInt(50000) + "");
            //System.out.println(x.toString() + " + " + y.toString() + " = " + x.add(y).toString());
            //System.out.println(x.toString() + " x " + y.toString() + " = " + x.multiply(y).toString());
        }

        // Sequential
        for (int i = 1; i <= MAX_FIB_X; i++) {
            long startTime = System.currentTimeMillis();
            BigInteger t = fibLoopBig(i);
            long endTime = System.currentTimeMillis();
            results.add(new Result("Loop F(" + i + ")", t, endTime - startTime, 1));
        }

        // Recursive
        for (int i = 1; i <= MAX_FIB_X; i++) {
            long startTime = System.currentTimeMillis();
            BigInteger t = fibRecurBig(new BigInteger(i+""));
            long endTime = System.currentTimeMillis();
            results.add(new Result("Recur F(" + i + ")", t, endTime - startTime, 1));
        }

        // Fib Matrix...

        ArrayList<ArrayList<String>> resultsTable = new ArrayList<ArrayList<String>>();
        resultsTable.add(0, new ArrayList<String>());
        resultsTable.get(0).add("Test Name");
        resultsTable.get(0).add("Value");
        resultsTable.get(0).add("Itererations");
        resultsTable.get(0).add("Avg. Time");
        for (int i = 0; i < results.size(); i++) {
            resultsTable.add(i + 1, new ArrayList<String>());
            resultsTable.get(i + 1).add(results.get(i).getTestName());
            resultsTable.get(i + 1).add("" + results.get(i).getValue());
            resultsTable.get(i + 1).add("" + results.get(i).getIterations());
            resultsTable.get(i + 1).add("" + results.get(i).getAverageTimePerIteration());
        }
        drawTable(resultsTable, 16, true);
    }

    public static BigInteger fibRecurBig(BigInteger x) throws InterruptedException {
        //Thread.sleep(1);
        if(x.compareTo(new BigInteger("0")) == 0 || x.compareTo(new BigInteger("1")) == 0) return x;
        return fibRecurBig(x.subtract(new BigInteger("1"))).add(fibRecurBig(x.subtract(new BigInteger("2"))));
    }

    public static BigInteger fibLoopBig(long x) throws InterruptedException {
        if(x == 0 || x == 1) return new BigInteger(x+"");

        //int a = 0, b = 1;
        BigInteger a = new BigInteger("0");
        BigInteger b = new BigInteger("1");
        for(int i = 2; i < x; i++) {
            BigInteger newValue = a.add(b);
            a = b;
            b = newValue;
            //Thread.sleep(1);
        }
        return b;
    }

    public static String padString(String str, int len, char filler, int mode) {
        if(str.length() > len) {
            str.substring(0,len-1);
        }
        while(str.length() < len) {
            if(mode == FormatMode.APPEND)
                str+=filler;
            else if(mode == FormatMode.PREPEND)
                str = filler + str;
        }
        return str;
    }

    public static void drawTable(ArrayList<ArrayList<String>> str, int cellWidth, boolean hasHeader) throws IndexOutOfBoundsException {
        boolean headerPrinted = false;
        System.out.println(" " + padString("", str.get(0).size()*cellWidth+str.get(0).size()*3+1, '#', FormatMode.APPEND));
        for(ArrayList<String> row : str) {
            for(String col : row) {
                System.out.print(" | " + padString(col, cellWidth, ' ', FormatMode.PREPEND));
            }
            System.out.println(" | "); //End the line.
            if(hasHeader && !headerPrinted) {
                System.out.println(" " + padString("", str.get(0).size()*cellWidth+str.get(0).size()*3+1, '#', FormatMode.APPEND));
                headerPrinted = true;
            }
        }
        System.out.println(" " + padString("", str.get(0).size()*cellWidth+str.get(0).size()*3+1, '#', FormatMode.APPEND));
    }
}
class Result {
    private String testName;
    private long timeTaken;
    private long iterations;
    private BigInteger value;

    public Result(String testName, BigInteger value, long timeTaken, long iterations) {
        this.testName = testName;
        this.timeTaken = timeTaken;
        this.iterations = iterations;
        this.value = value;
    }
    public String getTestName() {
        return this.testName;
    }
    public long getTimeTaken() {
        return this.timeTaken;
    }
    public long getIterations() {
        return this.iterations;
    }
    public float getAverageTimePerIteration() {
        return (float)this.timeTaken/this.iterations;
    }
    public String getValue() {
        return this.value.toString();
    }
}
class FormatMode {
    public static final int PREPEND = 0;
    public static final int APPEND = 1;
}