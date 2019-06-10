public class Multithreading {

    static final int size = 10000000;
    static final int h = size / 2;


    public static void main(String[] args) {
        // Для первого метода

        float[] arr1 = new float[size];
        fillTheMassiv(arr1);
        long a = System.currentTimeMillis();

        changeTheMassiv(arr1);
        long b = System.currentTimeMillis();
        System.out.println(b - a);


        //Для второго метода
        float[] arr2 = new float[size];
        fillTheMassiv(arr1);
        long c = System.currentTimeMillis();

        float[] a1 = new float[h];
        float[] a2 = new float[h];
        System.arraycopy(arr2, 0, a1, 0, h);
        System.arraycopy(arr2, h, a2, 0, h);

        new Thread(() -> changeTheMassiv(a1)).start();
        new Thread(() -> changeTheMassiv(a2)).start();

        System.arraycopy(a1, 0, arr2, 0, h);
        System.arraycopy(a2, 0, arr2, h, h);

        long d = System.currentTimeMillis();
        System.out.println(d - c);
    }


    public static void fillTheMassiv(float[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            arr[i] = 1;
        }
    }

    public static void changeTheMassiv(float[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}

