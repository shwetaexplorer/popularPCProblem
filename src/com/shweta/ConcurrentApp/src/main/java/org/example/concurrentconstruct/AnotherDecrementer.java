package org.example.concurrentconstruct;

import java.util.concurrent.CountDownLatch;

public class AnotherDecrementer implements Runnable {
    private CountDownLatch latch;

    public AnotherDecrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {

            latch.countDown();
            System.out.println("latch countdown happened " + (i+1) + " by " + Thread.currentThread().getName());
        }

    }
}
