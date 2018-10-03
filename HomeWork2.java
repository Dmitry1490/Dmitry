// Dmitry Bondarenko;
// Java lvl 1 HomeWork 2;

package ru.geekbrains;

import java.lang.reflect.Array;
import java.util.Arrays;

public class HomeWork2 {

    /* 1 Задать целочисленный массив, состоящий из элементов 0 и 1.
    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0; */

    private static void invArr(int[] arr){
        for (int i = 0; i < arr.length; i++){
            arr[i] = (arr[i] == 1) ? 0 : 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    /* 2 Задать пустой целочисленный массив размером 8. Написать метод,
    который помощью цикла заполнит его значениями 1 4 7 10 13 16 19 22;*/

    private static void fillArr(int[] arr){
        for (int i = 0; i < arr.length; i++){
            arr[i] = i*3 + 1;
        }
        System.out.println(Arrays.toString(arr));
    }

    /* 3 Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ],
    написать метод, принимающий на вход массив и умножающий числа меньше 6 на 2;*/

    private static void ifLess6(int[] arr){
        for (int i = 0; i < arr.length; i++){
            arr[i] = (arr[i] < 6) ? arr[i]*2 : arr[i];
        }
        System.out.println(Arrays.toString(arr));
    }

    /* 4 Задать одномерный массив. Написать методы поиска в нём минимального
    и максимального элемента;*/

    private static void minMaxArr(int[] arr){
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            min = (arr[i] < min) ? arr[i] : min;
        }
        for (int j = 0; j < arr.length; j++){
            max = (arr[j] > max) ? arr[j] : max;
        }
        System.out.printf(min + " " + max + "\n" );
    }

    /* 6 ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true
    если в массиве есть место, в котором сумма левой и правой части массива равны. Примеры:
    checkBalance([1, 1, 1, || 2, 1]) → true,
    checkBalance ([2, 1, 1, 2, 1]) → false,
    checkBalance ([10, || 1, 2, 3, 4]) → true.
    Абстрактная граница показана символами ||, эти символы в массив не входят.*/

    private static boolean balance(int[] arr) {
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 0; i < arr.length; i++) {
            sum1 += arr[i];
        }
        for (int j = (arr.length - 1); j > -1; j--) {
            sum1 -= arr[j];
            sum2 += arr[j];
            if (sum1 == sum2) { // помню про замечание что надо возвращать булево, но не знаю как по другому реализовать конструкцию
                return true;
            } else continue;
        }
        return sum1 == sum2;
    }


    public static void main(String[] args){
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0}; // Задаем целочисленный массив;
        invArr(arr1); // метод, заменяющий в принятом массиве 0 на 1, 1 на 0;

        int[] arr2 = new int[8]; // пустой целочисленный массив размером 8;
        fillArr(arr2); // {1 4 7 10 13 16 19 22};

        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1}; // массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ];
        ifLess6(arr3);// принимает на вход массив и умножающий числа меньше 6 на 2;

        int[] arr4 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 125, -5};
        minMaxArr(arr4);// минимальног и максимального элемент

    /* 5 * Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    заполнить его диагональные элементы единицами, используя цикл(ы);*/

        int[][] arr5 = new int[10][10];
        int rows = arr5.length; // строки
        int columns = arr5[0].length; // колонки
        for (int i = 0; i < rows;) { // цикл проводит диагональ с верхнего левого в нижний правый углы;
            for (int j = 0; j < columns; ) {
                arr5[i][j] = 1;
                i++;
                j++;
            }
        }
        columns = -columns;
        for (int i = 0; i < rows;) {  // цикл проводит диагональ с верхнего правого в нижний левый углы;
            for (int j = (arr5[0].length - 1); j > -1;) {
                arr5[i][j] = 1;
                i++;
                j--;
            }
        }

        for(int i = 0; i < arr5.length; i++){ // печвть массива;
            for(int j = 0; j < arr5[i].length; j++){
                System.out.print(arr5[i][j] + " ");
            }
            System.out.println();
        }


        //Данные для 6го задания;
        int[] arr6 = {10, 1, 2, 3, 4};
        System.out.println(balance(arr6));
    }
}

