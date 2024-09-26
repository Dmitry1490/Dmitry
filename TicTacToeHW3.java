// Dmitry Bondarenko;
// HomeWork3;
// Полностью разобраться с кодом;
//Переделать проверку победы, чтобы она не была реализована просто набором условий;
//* Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4;
//*** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока, и пытаться выиграть сам;

package ru.geekbrains;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeHW3 {
    private static final char Human = 'x'; // человек;
    private static final char II = 'o'; // компьютер;
    private static final char empty = '_'; // пустое поле;
    private static final Scanner SCANNER = new Scanner(System.in); // ввод
    private static final Random RANDOM = new Random(); // класс рандом;
    private static int fieldSizeY; // размер поля по У;
    private static int fieldSizeX; // размер поля по Х;
    private static char[][] field; // игровое поле;

    // создаем поле;
    private static void emptyField() {
        fieldSizeX = 3;
        fieldSizeY = 3;
        field = new char[fieldSizeX][fieldSizeY];
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = empty;
            }
        }
    }

    // печать поля;
    private static void printField() {
        System.out.println("_y_y_y_");
        for (int y = 0; y < fieldSizeY; y++) {
            System.out.print("x");
            for (int x = 0; x < fieldSizeX; x++) {
                System.out.print(field[y][x] + "|");
            }
            System.out.println();
        }
    }

    // ходит человек;
    private static void humanTurn() {
        int x;
        int y;
        do {
            System.out.print("Please enter coordinates X and Y (1 to 3) separated by space  ");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isEmptyCell(x, y) || !isValidCell(x, y));
        field[y][x] = Human;
    }

    // проверяем пустая ли клетка;
    private static boolean isEmptyCell(int x, int y) {
        return field[y][x] == empty;
    }

    // проверяем влезаем ли в игровое поле;
    private static boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    // ходит компьютер
    private static void aiTurn() {
        int x;
        int y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = II;
    }

    // проверяем заполненно ли игровое поле
    private static boolean isFieldFull() {
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmptyCell(x, y))
                    return false;
            }
        }
        return true;
    }

    // горизонтальный сканер
    private static boolean horizont() {
        int ihuman = 0;
        int iai = 0;
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == II) {
                    ihuman = 0;
                    iai++;
                } else if (field[y][x] == Human) {
                    ihuman++;
                    iai = 0;
                } else {
                    ihuman = 0;
                    iai = 0;
                }
            }
        }
        if (ihuman == 3) {
            System.out.println("Player win!");
        } else if (iai == 3) {
            System.out.println("Computer win!");
        }
        return (ihuman == 3 || iai == 3);
    }

    // вертикальный сканер
    private static boolean vertical() {
        int ihuman = 0;
        int iai = 0;
        for (int x = 0; x < fieldSizeX; x++) {
            for (int y = 0; y < fieldSizeY; y++) {
                if (field[y][x] == II) {
                    ihuman = 0;
                    iai++;
                } else if (field[y][x] == Human) {
                    ihuman++;
                    iai = 0;
                } else {
                    ihuman = 0;
                    iai = 0;
                }
            }
        }
        if (ihuman == 3) {
            System.out.println("Player wins!");
        } else if (iai == 3) {
            System.out.println("Computer wins!");
        }
        return (ihuman == 3 || iai == 3);
    }

    // сканер диагональный слева направо
    private static boolean leftToRight() {
        int ihuman = 0;
        int iai = 0;
        for (int i = 0; i < fieldSizeY; i++) {
            if (field[i][i] == II) {
                ihuman = 0;
                iai++;
            } else if (field[i][i] == Human) {
                ihuman++;
                iai = 0;
            } else {
                ihuman = 0;
                iai = 0;
            }
        }
        if (ihuman == 3) {
            System.out.println("Player wins!");
        } else if (iai == 3) {
            System.out.println("Computer wins!");
        }
        return (ihuman == 3 || iai == 3);
    }

    // сканер диагональный слева направо
    private static boolean rightToLeft() {
        int ihuman = 0;
        int iai = 0;
        for (int i = 0; i < fieldSizeY; i++) {
            if (field[i][field.length - 1 - i] == II) {
                ihuman = 0;
                iai++;
            } else if (field[i][field.length - 1 - i] == Human) {
                ihuman++;
                iai = 0;
            } else {
                ihuman = 0;
                iai = 0;
            }
        }
        if (ihuman == 3) {
            System.out.println("Player wins!");
        } else if (iai == 3) {
            System.out.println("Computer wins!");
        }
        return (ihuman == 3 || iai == 3);
    }

    public static void main(String[] args) {
        emptyField();
        printField();
//        while (true) {
        while (true) {
            humanTurn();
            printField();
            if (horizont()) {
                break;
            }
            if (vertical()) {
                break;
            }
            if (leftToRight()) {
                break;
            }
            if (rightToLeft()) {
                break;
            }
            if (isFieldFull()) {
                System.out.println("Draw!");
                break;
            }
            aiTurn();
            printField();
            if (horizont()) {
                break;
            }
            if (vertical()) {
                break;
            }
            if (leftToRight()) {
                break;
            }
            if (rightToLeft()) {
                break;
            }
            if (isFieldFull()) {
                System.out.println("Draw!");
                break;
            }
        }
        //play again?
        // if !y break;
    }
}