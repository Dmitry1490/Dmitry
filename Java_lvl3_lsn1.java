import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

//Задачий 1 и 2.

public class Java_lvl3_lsn1 {

    public static void main(String[] args) {
        Sequence<Integer> mas = new Sequence<Integer>();
        Integer[] mass = {1, 6};
        mass = mas.swap(mass);
        System.out.println(mass[0] + " " + mass[1]);

        ArrayList<Integer> arrayList = mas.masToArrayList(mass);
        System.out.println(arrayList.toString());

    }

    public static class Sequence<T> {

        //1. Написать метод, который меняет два элемента массива местами.(массив может быть любого ссылочного типа);

        public T[] swap(T[] massiv){
            if(massiv.length > 1){
                T x = massiv[0];
                massiv[0] = massiv[1];
                massiv[1] = x;
                return massiv;
            } else return massiv;
        }

        //2. Написать метод, который преобразует массив в ArrayList;

        public ArrayList<T> masToArrayList(T[] massiv){
            ArrayList<T> list = new ArrayList<T>();
            for (T t: massiv) {
                list.add(t);
            }
            return list;
        }
    }
}

