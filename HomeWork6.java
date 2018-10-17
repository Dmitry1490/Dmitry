// Dmitry Bondarenko
// HomeWork6

/*
1 Создать 2 текстовых файла, примерно по 50-100 символов в каждом(особого значения не имеет);
2 Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
3 * Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле.
4 ** Написать метод, проверяющий, есть ли указанное слово в папке
*/


import javax.imageio.IIOException;
import java.io.*;
import java.util.Scanner;

public class HomeWork6 {
    public static void main(String[] args) {

        // Создал 2 файла с текстом;

        try {
            PrintStream file1 = new PrintStream(new FileOutputStream("file1.txt"));
            file1.println(" Jaws. Introduction .The boy stopped, and the shark swam below him. Then it turned again." +
                    " The shark swam up fast. Its mouth opened... Amity is a quiet town near New York. Nothing" +
                    " happens there. One night a young woman goes for a swim in the sea. She doesn't come back.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            PrintStream file2 = new PrintStream(new FileOutputStream("file2.txt"));
            file2.println("The next morning the police find her dead on the beach. Brody is a good policeman, " +
                    " and he thinks there's a shark near Amity. Young Matt Hooper says it's a Great White shark - " +
                    " the fish they call the 'man-eater'. Brody tries to close the beaches, but the important people " +
                    " in Amity won't listen to him. Then, on a sunny afternoon, a young boy goes into the sea... ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Склеиваем два фаила;
        glueFile("file1.txt","file2.txt");

        // Поиск слова в файле;
        System.out.println(findWord("morning", "file3.txt"));

    }

    // Метод возвращает "1" если слово в фаиле нашлось и "0" если слово в тексте отсутствует;

    private static int findWord(String word, String namefile) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(namefile));
            while (scanner.hasNext()){
                if (scanner.next().equals(word)){
                    return 1;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } return 0;
    }


    // Метод склеивает два фаила;
    private static void glueFile(String namefile1, String namefile2){

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        try {
            FileInputStream fis = new FileInputStream(namefile1);
            int b;
            while ((b = fis.read()) != -1) {
                s1.append((char) b);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        try {
            FileInputStream fis = new FileInputStream(namefile2);
            int b;
            while ((b = fis.read()) != -1) {
                s2.append((char) b);
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // «Cклеиваю» эти файлы;

        try {
            PrintStream file3 = new PrintStream(new FileOutputStream("file3.txt", true));
            file3.print(s1 + "" + s2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
