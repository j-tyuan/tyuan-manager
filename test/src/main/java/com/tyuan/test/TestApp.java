package com.tyuan.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class TestApp {

    /*public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }*/

    public static void main(String[] args) {
        Object o = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println(new Date() + " Thread1 is running");
                    o.wait();
                    System.out.println(new Date() + " Thread1 ended");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            synchronized (o) {
                try {
                    System.out.println(new Date() + " Thread2 is running");
                    o.notify();
                    System.out.println(new Date() + " Thread2 notify");
                    // Don't use sleep method to avoid confusing
                    for (long i = 0; i < 200000; i++) {
                        for (long j = 0; j < 100000; j++) {
                        }
                    }
                    System.out.println(new Date() + " Thread2 release lock");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            for (long i = 0; i < 200000; i++) {
                for (long j = 0; j < 100000; j++) {
                }
            }
            System.out.println(new Date() + " Thread2 ended");
        });

        // Don't use sleep method to avoid confusing
        for (long i = 0; i < 200000; i++) {
            for (long j = 0; j < 100000; j++) {
            }
        }
        thread2.start();
    }
}
