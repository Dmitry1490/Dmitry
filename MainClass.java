/*3. Большая задача:

a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку
нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можете использовать ArrayList;
d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока -
1.0f, апельсина - 1.5f, не важно в каких это единицах);
e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в
compare в качестве параметра, true - если их веса равны, false в противном случае(коробки с яблоками мы можем
сравнивать с коробками с апельсинами);
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку
фруктов, нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается,
а в другую перекидываются объекты, которые были в этой коробке;
g. Не забываем про метод добавления фрукта в коробку.*/

import fruits.Apple;
import fruits.Orange;

public class MainClass {
    public static void main(String[] args) {

        Apple apple = new Apple();
        Orange orange = new Orange();


        BoxWithFruits<Apple> appleBox1 = new BoxWithFruits<Apple>(5);
        BoxWithFruits<Apple> appleBox2 = new BoxWithFruits<Apple>(6);
        BoxWithFruits<Orange> orangeBox = new BoxWithFruits<Orange>(5);

        appleBox1.addFruitInBox(apple);
        appleBox1.addFruitInBox(apple);
        appleBox1.addFruitInBox(apple);
        appleBox1.addFruitInBox(apple);
        System.out.println(appleBox1.getWeight());

        orangeBox.addFruitInBox(orange);
        orangeBox.addFruitInBox(orange);
        orangeBox.addFruitInBox(orange);
        System.out.println(orangeBox.getWeight());

        System.out.println(appleBox1.compare(orangeBox));

        appleBox1.intersperse(appleBox2);

        System.out.println(appleBox1.getWeight());
        System.out.println(appleBox2.getWeight());
    }
}

