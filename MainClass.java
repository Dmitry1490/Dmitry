import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static int finish = 0;
    private static Semaphore SEMAPHORE = new Semaphore(CARS_COUNT);


    public static void main(String[] args) throws InterruptedException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), SEMAPHORE);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();

        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
    }
}

