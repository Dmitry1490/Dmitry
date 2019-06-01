/*  Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При подаче массива другого
    размера необходимо бросить исключение MyArraySizeException.

    Далее метод должен пройтись по всем элементам массива, преобразовать в int и просуммировать. Если в каком-то
    элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть
    брошено исключение MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.

    В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и
    MyArrayDataException и вывести результат расчета.
    */


public class MainLsn2Java2 {

    public static void main(String[] args) {


        String[][] massiv1 = new String[3][3];
        String[][] massiv2 = {{"1", "2", "3", "4"} ,{"1", "2", "3", "4"},{"1", "2", "3", "4"},{"1", "2", "3", "4"}};
        String[][] massiv3 = {{"1", "Cool", "3", "4"} ,{"1", "2", "Cool", "4"},{"1", "2", "3", "4"},{"1", "2", "3", "4"}};


        try{
        System.out.println(converterAndSum(massiv1));
        }catch (MySizeArrayException e){
            System.out.println(e.getMessage());
        }

        System.out.println();

        try{
            System.out.println(converterAndSum(massiv2));
        }catch (MySizeArrayException e){
            System.out.println(e.getMessage());
        }

        System.out.println();


        try{
            System.out.println(converterAndSum(massiv3));
        }catch (MySizeArrayException e){
            System.out.println(e.getMessage());
        }


    }

    private static int converterAndSum(String[][] massiv) throws MySizeArrayException {
        if (massiv[0].length !=4 & massiv[1].length !=4) throw new MySizeArrayException("array must be 4x4");

        int k = 0;
        int[][] array = new int[4][4];;


        for (int i = 0; i < massiv[0].length; ++i){
            for(int j = 0; j < massiv[i].length; j++) {

                try{

                    try {
                        array[i][j] = Integer.parseInt(massiv[i][j]);
                        k += array[i][j];
                    } catch (NumberFormatException e) {

                        /* Создал класс MyArrayDataException, унаследовался от NumberFormatException, но в
                        MyArrayDataException заходить отказывается. C NumberFormatException работает корректно
                        */

                        throw new MyArrayDataException("wrong data type");
                    }

                } catch (MyArrayDataException e){
                    System.out.println(e.getMessage());
                }
        }

    }
        return k;
    }


}





