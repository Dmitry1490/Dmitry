import java.util.Arrays;

public class OneOrFourCheck {
    /*  Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или
    единицы, то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).
    */



    public boolean oneOrFourCheck(Integer[] mas) {
        boolean status = false;

        if(Arrays.asList(mas).contains(Integer.valueOf(1)) & Arrays.asList(mas).contains(Integer.valueOf(4)))
            status = true;

        return status;
    }
}
