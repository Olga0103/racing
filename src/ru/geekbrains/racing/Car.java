package ru.geekbrains.racing;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;
    private CyclicBarrier barrier;
    private ArrayBlockingQueue<Car> finished;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier, ArrayBlockingQueue<Car> finished) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.barrier = barrier;
        this.finished = finished;
    }

    @Override
    public void run() {
       /* try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            barrier.await();
            barrier.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);

            }
            finished.put(this);
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        } */

        }



    public Runnable start() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            barrier.await();
           // barrier.await();

            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);

            }
            finished.put(this);
            barrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this::run;
    }



    }


//        CyclicBarrier cb = new CyclicBarrier(3);
//
//        for (int i = 0; i < race.getStages().size(); i++) {
//
//            new Thread(() -> {
//                try {
//                    System.out.println(this.name + " begin");
//                    Thread.sleep(2000 + this.name * 2000);
//                    System.out.println(this.name + " ready");
//                    cb.await();
//                    System.out.println(this.name + " START");
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }





