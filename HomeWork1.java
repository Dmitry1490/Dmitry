// Dmitry Bondarenko
// Java lvl 1 HomeWork 1

package ru.geekbrains;

public class HomeWork1 {

    /** Метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат
     с плавающей точкой, где a, b, c, d целочисленные входные параметры этого метода; */

    private static double calculate(int a,int b, int c, int d){
        double result = (double)a * ((double)b + ((double)c / (double)d));
        return result;
    }

    /** Метод, принимающий на вход два целых числа, и проверяющий что их сумма лежит в пределах от 10 до
     20(включительно), если да – вернуть true, в противном случае – false; */

    private static boolean t​ask10and20(int num1, int num2){
        if (10 <= (num1 + num2) && (num1 + num2) <= 20) {
            return true;
        } else {return false;}
    }

    /** Метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль и проверить
     положительное ли число передали, или отрицательное. Замечание: ноль считаем положительным числом.
     Результат работы метода вывести в консоль; */

    private static void PositiveOrNegative(int numeric){
        if (numeric >= 0){
            System.out.println("Numeric is positive");
        } else {
            System.out.println("Numeric is negative");
        }

    }

    /** Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вернуть
     приветственное сообщение «Привет, переданное_имя!»; Вывести приветствие в консоль; */

    private static void HelloName(String name){
        System.out.println("Hello, " + name + '!');
    }

    /** Метод, который определяет является ли год високосным. Каждый 4-й год является високосным, кроме каждого 100-го,
     при этом каждый 400-й – високосный. Для проверки работы вывести результаты работы метода в консоль; */

    private static void LeapYear(int year){
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)){
            System.out.println("This year is a leap year");
        } else {
            System.out.println("This year is't a leap year");
        }
    }

    public static void main(String[] args){
        double x = calculate(5,2,3,4);
        System.out.println(x); // результат выражения a * (b + (c / d));

        boolean num = t​ask10and20(1, 3);
        System.out.println(num); // возвращает true если число в пределах от 10 до 20(включительно);

        PositiveOrNegative(-7); // Положительные и отрицательные числа;

        HelloName("Dmitry"); // вывод «Привет, переданное_имя!»;

        LeapYear(1700); // в случае если год високосный - выводится "This year is a leap year";
                        // в случае если год не является високосным - выводится "This year is a leap year";
    }
}
