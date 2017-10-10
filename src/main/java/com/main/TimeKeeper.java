package com.main;

/**
 * Created by Jaden on 6/4/2015.
 */
public class TimeKeeper {
    private long startTime;

    public void start() {
        this.startTime = System.nanoTime();
    }
    public void start(String msg) {
        System.out.println(msg);
        this.start();
    }
    public long stop() {
        long endTime = System.nanoTime();
        long difference = endTime - startTime;
        System.out.println("Operation finished in " + ((double)difference / 1000000000D) + " s");
        return difference;
    }
}