package org.example.util;

public class ConcurrencyTest {
    public static void main(String[] args) {

        DatabaseResource db = new DatabaseResource();
        Object detail = new Object();
        // create 10 threads for display metrics

        Thread[] threads = new Thread[20];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                db.displayMetrics(detail);
                }
            });
        }
        //create 10 threads for read metrics
        for (int i = 10; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    db.readMetrics();
                }
            });
        }
        //start all the threads
        for (Thread thread : threads) {
            thread.start();
        }

    }
}
