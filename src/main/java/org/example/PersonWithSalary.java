package org.example;

public class PersonWithSalary {

    //variables
    private final String name;
    private final int age;
    private final Gender gender;
    private int salary;

    //constructor
    PersonWithSalary (String name, int age, Gender gender, int salary) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

//    // getters
    public String getName() {return name;};
//    public int getAge() {return age;};
//    public Gender getGender() {return gender;};
    public int getSalary() {return salary;};

    //methods
    // increments salary of Person by 1.1 ex
    public void salaryIncrement(double increment) {
        salary = (int) (getSalary() * increment);
    }

    public boolean findByName(PersonWithSalary person) {
        return person.getName().equals(name);
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", salary=" + salary +
                '}';
    }
}
