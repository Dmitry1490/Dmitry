// Dmitriy Bondarenko;
// HomeWork4

package ru.geekbrains;

public class mainHW4 {
    public static void main(String[] args) {
        Employees emp1 = new Employees("Ivanov Ivan Ivanovich", 10000, 20);
        Employees emp2 = new Employees("Ivanovsiy Igor Aleksandrovich", 15000, 22);
        Employees emp3 = new Employees("Petrov Evgeniy Viktorovich", 35000, 35);
        Employees emp4 = new Employees("Solcev Yaroslav Voktorovich ", 25000, 45);
        Employees emp5 = new Employees("Guzeeva Ekaterina Pavlovna ", 37005, 27);

        // Вывести в консоль при помощи методов из пункта 3 ФИО и возраст.
        System.out.println(emp1.getFio());
        System.out.println(emp1.getAge());

        // Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
        Employees[] allEmp = new Employees[5];
        allEmp[0] = emp1;
        allEmp[1] = emp2;
        allEmp[2] = emp3;
        allEmp[3] = emp4;
        allEmp[4] = emp5;

        for (int i = 0; i < allEmp.length; i++) {
            if (allEmp[i].getAge() > 40) {
                System.out.println(allEmp[i].getFio() + allEmp[i].getSalary() + " " + allEmp[i].getAge());
            }
        }

        // увеличиваю зарплату на 5000;
        emp5000(allEmp);

        // средний возраст;
        System.out.println(averageAge(allEmp));

        //средняя зарплата;
        System.out.println(averageSalary(allEmp));


    }
//* Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000.
    private static void emp5000(Employees[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getAge() > 40) {
                arr[i].raiseTheSalary();
                System.out.println(arr[i].getFio() + arr[i].getSalary() + " " + arr[i].getAge());
            }
        }
    }

    /* Написать методы (принимающие на вход массив сотрудников), вычисляющие
    средний возраст и среднюю зарплату сотрудников, вывести результаты работы в консоль.*/
    private static float averageAge(Employees[] arr){
        int avr = 0;
        float j = 0;
        for (int i = 0; i < arr.length; i++) {
            avr += arr[i].getAge();
            j++;
        }
        return (avr / j);
    }

    private static float averageSalary(Employees[] arr){
        int avr = 0;
        float j = 0;
        for (int i = 0; i < arr.length; i++) {
            avr += arr[i].getSalary();
            j++;
        }
        return (avr / j);
    }
}


