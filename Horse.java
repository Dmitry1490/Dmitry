package ru.geekbrains;

public class Horse extends Animal{

    private int distSwim;

    Horse(int distRun, double jumpHeight, int distSwim) {

        super(distRun, jumpHeight, 1500, 3, "The horse ");

        if (distSwim <= 100 && distSwim > 0) {
            this.distSwim = distSwim;
        } else {
            System.out.println("The horse cant swim so far");
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