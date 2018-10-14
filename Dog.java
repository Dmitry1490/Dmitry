package ru.geekbrains;

public class Dog extends Animal {

    private int distSwim;


    Dog(int distRun, double jumpHeight, int distSwim) {


        super(distRun, jumpHeight, 500,0.5, "The dog ");

        if (distSwim <= 10 && distSwim > 0) {
            this.distSwim = distSwim;
        } else {
            System.out.println("The dog cant swim so far");
        }
    }

    protected int getDistSwim () {
        return this.distSwim;
    }

    @Override
    protected void Swim(int dist){
        if (getDistSwim() > dist) {
            System.out.println(getName() + "was able to swim!");
        }
    }
}