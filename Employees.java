package ru.geekbrains;

public class Employees {
    // Создать класс "Сотрудник" с полями: ФИО,зарплата, возраст;
    private static final int sum = 5000;
    private String FIO;
    private int salary;
    private int age;

   // Конструктор класса должен заполнять эти поля при создании объекта;

    Employees(String FIO, int salary, int age){
        this.FIO = FIO;
        this.salary = salary;
        this.age = age;
    }

    // Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;

    String getFio (){
        return this.FIO;
    }
    int getSalary(){
        return this.salary;
    }
    int getAge(){
        return this.age;
    }
    int raiseTheSalary(){
        return (this.salary = salary + sum);
    }





}
