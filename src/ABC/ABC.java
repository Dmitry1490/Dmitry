package ABC;

public class ABC{

    private static final Object lock = new Object();


    public static class A implements Runnable {
        private volatile char currentLetter = 'A';
        @Override
        public void run() {
            synchronized (lock)
            {
                System.out.println(currentLetter);
            }
        }
    }

    public static class B implements Runnable {
        private volatile char currentLetter = 'B';
        @Override
        public void run() {
            synchronized (lock)
            {
                System.out.println(currentLetter);
            }
        }
    }

    public static class C implements Runnable {
        private volatile char currentLetter = 'C';
        @Override
        public void run() {
            synchronized (lock)
            {
                System.out.println(currentLetter);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new A());
        Thread t2 = new Thread(new B());
        Thread t3 = new Thread(new C());

        for (int i = 0; i < 5; i++){
            t1.run();
            t2.run();
            t3.run();
            t1.join();
            t2.join();
            t3.join();
        }
    }






}
