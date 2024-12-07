package org.example.concurrentconstruct;

import java.util.concurrent.CountDownLatch;

public class CDLatchTest {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(4);
        //waiter
        Waiter waiter = new Waiter(latch);
        // decrementor
        Decrementer decrementer = new Decrementer(latch);

        AnotherDecrementer anotherDecrementer = new AnotherDecrementer(latch);
        new Thread(waiter).start();
        new Thread(decrementer).start();
        new Thread(anotherDecrementer).start();

    }
}
