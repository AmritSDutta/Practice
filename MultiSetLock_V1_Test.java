package com.trs.estimation;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MultiSetLock_V1_Test {

    public static void main(String args[]) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                Set<String> lockItems = Stream.of("1", "2", "3").collect(Collectors.toSet());
                MultiSetLock_V1 m1 = new MultiSetLock_V1(lockItems);
                m1.lock();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }

                m1.unlock();
            }
        };


        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                Set<String> lockItems = Stream.of("1", "4", "3").collect(Collectors.toSet());
                MultiSetLock_V1 msl1 = new MultiSetLock_V1(lockItems);
                msl1.lock();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }
                msl1.unlock();
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                Set<String> lockItems = Stream.of("9", "10", "8").collect(Collectors.toSet());
                MultiSetLock_V1 msl1 = new MultiSetLock_V1(lockItems);
                msl1.lock();
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    System.out.println(e);
                }
                msl1.unlock();
            }
        };


        for (int i = 0; i < 2000; i++) {

            new Thread(r).start();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println(e);
            }
            new Thread(r1).start();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.out.println(e);
            }
            new Thread(r2).start();
        }

        try {
            Thread.sleep(20000);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Finished UseCase : " + MultiSetLock_V1.getLockCount());

    }
}
