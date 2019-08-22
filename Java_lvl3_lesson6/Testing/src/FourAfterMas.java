public class FourAfterMas {
    /*
    *   Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод
    *   должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после
     *  последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить
     *  RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
     *  Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
    */

    public int[] fourAfterMassiv(int[] mas) throws RuntimeException{
        if(mas == null){
            throw new NullPointerException("Massive is null");
        }
        if(mas.length == 0){
            System.out.println("Massive is empty");
        }

        int[] fourAfterMas = null;
        int count;
        boolean flag = false;

        for (int i = mas.length - 1; i >= 0; i--)
        {
            count = i;
            if(mas[i] == 4){
                flag = true;
                fourAfterMas = new int[mas.length - 1 - count];
                for (int j = 0; j < fourAfterMas.length; j++){
                    count++;
                    fourAfterMas[j] = mas[count];
                }
                break;
            }
        }

        if(!flag){
            throw new RuntimeException("Four not found");
        }

        return fourAfterMas;
    }
}
