package org.example.concurrentconstruct;

import java.util.concurrent.CountDownLatch;

public class Decrementer implements Runnable {
    private CountDownLatch latch;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {

            latch.countDown();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("latch countdown happened " + (i+1) + " by " + Thread.currentThread().getName());
        }

    }
}
