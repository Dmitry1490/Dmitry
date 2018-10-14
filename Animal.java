package ru.geekbrains;

import java.security.Signature;

public abstract class Animal {
    protected int distRun;
    protected double jumpHeight;
    protected int limitrun;
    protected double limitjump;
    protected String name;

    protected Animal(int distRun, double jumpHeight, int limitrun, double limitjump, String name){

        if (distRun <= limitrun && distRun > 0) {
            this.distRun = distRun;
        } else {
            System.out.println( name + "cant run so much");
        }

        if (jumpHeight <= limitjump && jumpHeight > 0) {
            this.jumpHeight = jumpHeight;
        } else {
            System.out.println( name + "cant jump on high");
        }

        this.name = name;
    }

    protected int getDistRun(){
        return this.distRun;
    }

    protected double getJumpHeight(){
        return this.jumpHeight;
    }

    protected  String getName(){
        return this.name;
    }

    protected void Run(int dist){
        if (getDistRun() > dist) {
            System.out.println(getName() + "was able to run!");
        }
    }

    protected void Jump(double height){
        if (getJumpHeight() > height) {
            System.out.println(getName() + "was able to jump!");
        }
    }

    abstract protected void Swim(int dist);

}