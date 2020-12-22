package ru.geekbrains.racing;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("Важное объявление >>> ПОДГОТОВКА!!!");

        CyclicBarrier barrier = new CyclicBarrier(Main.CARS_COUNT + 1);
        ArrayBlockingQueue<Car> finished = new ArrayBlockingQueue<>(Main.CARS_COUNT);

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), barrier, finished);
            new Thread(cars[i].start());

        }

        try {
            barrier.await();
            System.out.println("Важное объявление >>> Гонка началась!!!");
            barrier.await();
            System.out.println("ПОБЕДИЛ: " + finished.take().getName());
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();

        }


        System.out.println("Важное объявление >>> Гонка закончилась!!!");

    }

}
