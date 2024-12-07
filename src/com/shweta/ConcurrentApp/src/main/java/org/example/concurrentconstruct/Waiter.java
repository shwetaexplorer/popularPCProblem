package org.example.concurrentconstruct;

import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable {

    private CountDownLatch latch;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for " + Thread.currentThread().getName());
            latch.await();
            Thread.sleep(1000);
            System.out.println("Wait completed for " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
