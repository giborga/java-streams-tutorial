package org.example;

public class Person {

    //variables
    private final String name;
    private final int age;
    private final Gender gender;

    //constructor
    Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // getters
    public String getName() {
        return name;
    }

    ;

    public int getAge() {
        return age;
    }

    ;

    public Gender getGender() {
        return gender;
    }

    ;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}


//enum GENDER {MALE, FEMALE};
