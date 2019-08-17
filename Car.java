import java.io.CharArrayReader;
import java.util.concurrent.Semaphore;

public class Car implements Runnable {
    private static volatile Integer CARS_COUNT;
    private Integer START_CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    private Semaphore semaphore;
    private boolean Win;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, Semaphore semaphore) {
        this.race = race;
        this.speed = speed;
        this.semaphore = semaphore;
        CARS_COUNT++;
        this.START_CARS_COUNT = CARS_COUNT;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        synchronized (CARS_COUNT){
            CARS_COUNT--;
            if(START_CARS_COUNT-CARS_COUNT == 1){System.out.println(this.name + " Win!");}
            if(CARS_COUNT==0){System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");}
        }
    }
}
