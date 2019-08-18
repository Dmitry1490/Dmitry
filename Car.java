
import java.util.concurrent.CountDownLatch;

public class Car implements Runnable {
    private static volatile Integer CARS_COUNT;
    private static volatile Integer START_CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    //private Semaphore semaphore;
    private CountDownLatch countDownLatch;
    private boolean Win;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CountDownLatch countDownLatch) {
        this.race = race;
        this.speed = speed;
        this.countDownLatch = countDownLatch;
        CARS_COUNT++;
        this.START_CARS_COUNT = CARS_COUNT;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            countDownLatch.countDown();
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            countDownLatch.countDown();
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");

        } catch (Exception e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }

        synchronized (CARS_COUNT){
            CARS_COUNT--;
            if(START_CARS_COUNT - CARS_COUNT == 1){System.out.println(this.name + " Win!");}
            if(CARS_COUNT==0){System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");}
        }
    }
}
