package ru.geekbrains;

public class Bird extends Animal{

    Bird (int distRun, double jumpHeight) {

        super(distRun, jumpHeight, 5,0.2, "The bird ");

    }

    @Override
    protected void Swim(int dist){
        System.out.println("Birds dont swim!");
    }
}