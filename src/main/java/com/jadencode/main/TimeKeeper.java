package com.jadencode.main;

/**
 * Created by Jaden on 6/4/2015.
 */
public class TimeKeeper {
    private long startTime;
    private long endTime;

    public void start() {
        this.startTime = System.nanoTime();
    }

    public void start(String msg) {
        System.out.println(msg);
        this.start();
    }

    public void stop() {
        this.endTime = System.nanoTime();
    }

    public void display() {
        System.out.println("Operation finished in " + ((double) (endTime - startTime) / 1000000000D) + " s");
    }

    public void stopAndDisplay() {
        this.stop();
        this.display();
    }
}