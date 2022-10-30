package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TheStreams {
    public static void main(String[] args) {

        //https://stackify.com/streams-guide-java-8/

        List<PersonWithSalary> peopleList = getPeopleWithSalary();

        // applySalaryForEachEmployee
        // orEach() is a terminal operation, which means that, after the operation is performed, the stream pipeline is
        // considered consumed, and can no longer be used
        peopleList.stream().forEach(e -> e.salaryIncrement(1.33));

        //peopleList.forEach(System.out::println);

        // map() produces a new stream after applying a function to each element of the original stream.
        // The new stream could be of different type.


        //Streams with HashSet - fast
        List<PersonWithSalary> filteredList;
        Set<String> nameFilterSet = employeeNameFilter().stream().collect(Collectors.toSet()); //repackaging elements to some data structures and applying some additional logic, concatenating them, etc.) on data elements held in the Stream instance

        filteredList = peopleList.stream()
                .filter(employee -> nameFilterSet.contains(employee.getName()))
                .collect(Collectors.toList());

        filteredList.forEach(System.out::println);

    }

        private static List<String> employeeNameFilter() {
        return Arrays.asList("alicia keys", "tom waits", "moby");
    }
        private static List<Person> getPeople () {
            return List.of(
                    new Person("alicia keys", 33, Gender.FEMALE),
                    new Person("tom waits", 33, Gender.MALE),
                    new Person("moby", 44, Gender.MALE),
                    new Person("edith piaf", 55, Gender.FEMALE),
                    new Person("bonobo", 66, Gender.FEMALE),
                    new Person("tom rosenthal", 77, Gender.MALE),
                    new Person("david bowie", 88, Gender.MALE),
                    new Person("eric clapton", 87, Gender.MALE),
                    new Person("jim morrison", 54, Gender.MALE),
                    new Person("billy joel", 21, Gender.MALE),
                    new Person("kurt obain", 21, Gender.MALE),
                    new Person("betty davis", 21, Gender.FEMALE),
                    new Person("grace jones", 123, Gender.FEMALE),
                    new Person("nina simone", 7, Gender.FEMALE)
            );
        }

        private static List<PersonWithSalary> getPeopleWithSalary () {
            return List.of(
                    new PersonWithSalary("alicia keys", 33, Gender.FEMALE, 36000),
                    new PersonWithSalary("tom waits", 33, Gender.MALE, 73000),
                    new PersonWithSalary("moby", 44, Gender.MALE, 30000),
                    new PersonWithSalary("edith piaf", 55, Gender.FEMALE, 21000),
                    new PersonWithSalary("bonobo", 66, Gender.FEMALE, 6000),
                    new PersonWithSalary("tom rosenthal", 77, Gender.MALE, 39000),
                    new PersonWithSalary("david bowie", 88, Gender.MALE, 39000),
                    new PersonWithSalary("eric clapton", 87, Gender.MALE, 39000),
                    new PersonWithSalary("jim morrison", 54, Gender.MALE, 36000),
                    new PersonWithSalary("billy joel", 21, Gender.MALE, 36000),
                    new PersonWithSalary("kurt obain", 21, Gender.MALE, 5000),
                    new PersonWithSalary("betty davis", 21, Gender.FEMALE, 21000),
                    new PersonWithSalary("grace jones", 123, Gender.FEMALE, 42000),
                    new PersonWithSalary("nina simone", 7, Gender.FEMALE, 1000)
            );
        }

        private static void getTutorial () {
            // youtube.com/watch?v=Q93JsQ8vcwY&t=903s
/*        List<Person> females = new ArrayList<>(); // Constructs an empty list with an initial capacity of ten.

        // imperative approach
        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }

        females.forEach(System.out::println); */
            List<Person> people = getPeople();

            // declarative approach: people.stream() creates stream-abstraction
            // extract to variable shortcut: option+command+V
            //filter
            List<Person> femalesFilt = people.stream()
                    .filter(person -> person.getGender().equals(Gender.FEMALE)) // predicate == true
                    .collect(Collectors.toList()); // collect result back to list

            //femalesFilt.forEach(System.out::println);

            //sort: by age then by gender + reversed
            List<Person> peopleSort = people.stream()
                    .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
                    .toList(); // collect result back to list - same

            //peopleSort.forEach(System.out::println);

            //allMatch : all are older than 1
            boolean peopleMatch = people.stream()
                    .allMatch(person -> person.getAge() > 1);

            //System.out.println(peopleMatch);

            //noneMatch : no name 'Eric'
            boolean peopleNoneMatch = people.stream()
                    .noneMatch(person -> person.getName().equals("Eric"));

            //System.out.println(peopleNoneMatch);

            //max
            Optional<Person> max = people.stream() // returns optional because thre might not bex MAX
                    .max(Comparator.comparing(Person::getAge));
            //.ifPresent(person -> System.out.println(person));

            //min
            people.stream() // returns optional because thre might not bex MAX
                    .min(Comparator.comparing(Person::getAge));
            //.ifPresent(System.out::println);

            //group: based on gender. expect map of gender, a list of each gender, and list of person within each gender
            //groupingBy takes actual field we want to group by (gender)
            Map<Gender, List<Person>> peopleGroup = people.stream()
                    .collect(Collectors.groupingBy(Person::getGender));

            peopleGroup.forEach((gender, group) -> { // for each k-v pair
                System.out.println(gender); //print key
                group.forEach(System.out::println); //print each person from value list
                System.out.println(); // newline
            });

            // take name of the oldest woman
            // map() used to transform each element of the stream by applying a function to each element
            // map() an intermediate operation, and it returns a stream of the transformed element.
            Optional<String> s = people.stream()
                    .filter(person -> person.getGender().equals(Gender.FEMALE))
                    .max(Comparator.comparing(Person::getAge)) //Optional[Person{name='Ira Pali', age=123, gender=FEMALE}]
                    .map(Person::getName); //getName() from stream to stream

            s.ifPresent(System.out::println);
        }
    }