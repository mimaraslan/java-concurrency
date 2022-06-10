package com.mimaraslan._007_ThreadLocalRandom;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.ThreadLocalRandom;

public class AppMain {

    public static void main(final String[] arguments) {
        System.out.println("Random Integer: " + new Random().nextInt());
        System.out.println("Seeded Random Integer: " + new Random(15).nextInt());
        System.out.println("Thread Local Random Integer: " + ThreadLocalRandom.current().nextInt());

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        random.setSeed(15); //exception will come as seeding is not allowed in ThreadLocalRandom.
        System.out.println("Seeded Thread Local Random Integer: " + random.nextInt());
    }
}

