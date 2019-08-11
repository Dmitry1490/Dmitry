package ABC;

/*  Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
*   Используйте wait/notify/notifyAll. */

public class ABСABСABС {
    private static final Object lock = new Object();


    public static class PausedThread extends Thread {
        private boolean flag = false;
        private boolean stop = false;
        char c;

        PausedThread(char c){
            this.c = c;
        }

        @Override
        public void run() {
            synchronized(lock) {
                while(!stop) {
                    try {
                        if(flag) lock.wait();    // приостанавливает поток если выполняется условие
                        printC();
                        pauseThread();
                    } catch (InterruptedException ex) { }
                }
            }
        }

        public void printC(){
            System.out.println(this.c);
        }

        public void pauseThread() {
            flag = true;
        }

        public void continueThread() {
            flag = false;
            synchronized(lock) {
                lock.notify();   // пробуждает приостановленый поток на обьекте lock
            }
        }

        public void stopThread() {
            stop = true;   // завершает поток
        }

    }



    public static void main(String[] args) throws InterruptedException {

        int i = 0;

        PausedThread t1 = new PausedThread('A');
        PausedThread t2 = new PausedThread('B');
        PausedThread t3 = new PausedThread('C');


        t1.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);
        t3.start();

        while (i < 4){
            t1.continueThread();
            Thread.sleep(100);
            t2.continueThread();
            Thread.sleep(100);
            t3.continueThread();
            i++;
        }

    }

}
