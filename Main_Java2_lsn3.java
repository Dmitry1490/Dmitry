
import java.util.*;

public class Main_Java2_lsn3 {

    public static void main(String[] args) {

        /*1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список
        уникальных слов, из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое
        слово.*/

        String[] arr = new String[15];

        arr[0] = "cat";
        arr[1] = "dog";
        arr[2] = "fat";
        arr[3] = "rat";
        arr[4] = "bird";
        arr[5] = "beer";
        arr[6] = "guest";
        arr[7] = "doctor";
        arr[8] = "actor";
        arr[9] = "student";
        arr[10] = "dog";
        arr[11] = "fat";
        arr[12] = "rat";
        arr[13] = "bird";

        // Найти и вывести список уникальных
        // слов, из которых состоит массив (дубликаты не считаем).
        Set<String> mySet = new HashSet<>(Arrays.asList(arr));
        System.out.println("Cписок уникальных слов");
        System.out.println(mySet + "\n");


        // Посчитать, сколько раз встречается каждое слово.
        Map<String, Integer> dubbleMap = new HashMap<>();
        ArrayList<String> myList = new ArrayList<>(Arrays.asList(arr));

        for (int i = 0; i < myList.size(); i++) {

            String tempString = myList.get(i);

            if(!dubbleMap.containsKey(tempString)){
                dubbleMap.put(tempString, 1);}
                else {
                    dubbleMap.put(tempString, dubbleMap.get(tempString) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : dubbleMap.entrySet() ) {
            System.out.println("Строка " + entry.getKey() + ", Повторений " + entry.getValue());
        }
        System.out.println("\n");

        /* 2. Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
         *  В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get()
         *  искать номер телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов
         *  (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны. Желательно не
         *  добавлять лишний функционал (дополнительные поля (имя, отчество, адрес), взаимодействие с пользователем
            через консоль и т.д). Консоль использовать только для вывода результатов проверки телефонного справочника.
         * */

        Telefonbook telefonbook = new Telefonbook();

        telefonbook.add("IVAN", 123456789);
        telefonbook.add("IVAN", 12345678);
        telefonbook.add("VASIY", 987654321);

        telefonbook.get("VASI");
    }
}


