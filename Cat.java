package ru.geekbrains;

public class Cat extends Animal {

    Cat(int distRun, double jumpHeight) {

        super(distRun, jumpHeight, 200,2, "The cat ");

    }

    @Override
    protected void Swim(int dist){
            System.out.println("Cats dont swim!");
        }
}
