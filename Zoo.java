// Dmitry Bondarenko
// Zoo Y1 HomeWork5

/*
1. Создать классы Собака, Лошадь, Птица и Кот с наследованием от класса Животное.
2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу
передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.,;
прыжок: кот 2 м., собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м.,
лошадь 100 м.).
4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат. (Например, dog1.run(150); ->
результат: 'Пёсик пробежал!')
5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой
600 м.*/

package ru.geekbrains;

public class Zoo {
    public static void main(String[] args) {
        Dog g = new Dog(500, 0.4, 6);
        Cat c = new Cat(100, 1.6);
        Horse h = new Horse(1409, 2.5, 77);
        Bird b = new Bird(5, 0.2);

        Animal[] zoo = {g, c, h, b};

        fullRun(zoo,15);

        fullJump(zoo,0.1);

        fullSwim(zoo, 50);

    }
    // Марафон по бегу;
    private static void fullRun(Animal[] arr, int dist) {
        for (int i = 0; i < arr.length; i++) {
            arr[i].Run(dist);
        }
    }
    // Марафон по прыжкам;
    private static void fullJump(Animal[] arr, double high) {
        for (int i = 0; i < arr.length; i++) {
            arr[i].Jump(high);
        }
    }
    // Марафон по плаванию;
    private static void fullSwim(Animal[] arr, int dist) {
        for (int i = 0; i < arr.length; i++) {
            arr[i].Swim(dist);
        }
    }


}

