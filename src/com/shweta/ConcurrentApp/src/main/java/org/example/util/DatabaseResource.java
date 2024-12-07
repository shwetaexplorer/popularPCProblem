package org.example.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DatabaseResource {

    private Lock displayMetrickLock  = new ReentrantLock();
    private Lock readMetrickLock  = new ReentrantLock();

    public void displayMetrics(Object detail)  {
        displayMetrickLock.lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Display metrics for Thread " + detail + Thread.currentThread().getName() );
        displayMetrickLock.unlock();

    }

    public void readMetrics()  {
        readMetrickLock.lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Read metrics for Thread " + Thread.currentThread().getName() );
        readMetrickLock.unlock();

    }
}
