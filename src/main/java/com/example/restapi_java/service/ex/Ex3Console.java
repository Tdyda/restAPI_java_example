package com.example.restapi_java.service.ex;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Ex3Console {
    List<Person> people = new ArrayList<>(
            Arrays.asList(
                    new Person("Tomasz", 28),
                    new Person("Adrian", 28),
                    new Person("Paulina", 26),
                    new Person("Michał", 24),
                    new Person("Rafał", 53),
                    new Person("Jolanta", 57),
                    new Person("Jadwiga", 53),
                    new Person("Daniel", 48)
            )
    );

    List<String> stringWithNull = new ArrayList<>(
            Arrays.asList(
                    "Asia",
                    null,
                    "Bartek",
                    null,
                    "Ania"
            )
    );

    List<Product> products = Arrays.asList(
            new Product("Laptop", 2500.0, 5),
            new Product("IPad", 1200.0, 3),
            new Product("Smartphone", 1500.0, null),
            new Product("Headphones", 300.0, 4),
            new Product("Monitor", 800.0, 4),
            new Product("Keyboard", 200.0, null),
            new Product("Tablet", 1200.0, 3)
    );


    public void print() {
        // 1. Posortuj listę liczb rosnąco.
        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 7);

        List<Integer> ascending = numbers.stream().sorted().collect(Collectors.toList()); // mutowalna tablica
        System.out.println(ascending);
        ascending.add(2);
        System.out.println(ascending);


        breakLine();

        // 2. Posortuj listę liczb malejąco.
        List<Integer> descending = numbers.stream().sorted(Comparator.reverseOrder()).toList(); // niemutowalna tablica
        System.out.println(descending);

        breakLine();

        // 3. Posortuj listę stringów alfabetycznie (A → Z).
        System.out.println("Posortuj listę stringów alfabetycznie (A → Z)");
        List<String> names = Arrays.asList("Kasia", "Bartek", "Ania", "Tomek");
        List<String> alphabeticalOrder = names.stream().sorted(String::compareTo).collect(Collectors.toList()); // mutowalna
        System.out.println(alphabeticalOrder);

        breakLine();

        // 4. Posortuj listę stringów według długości (najkrótsze → najdłuższe).
        System.out.println("Posortuj listę stringów według długości (najkrótsze → najdłuższe)");
        List<String> sortedList = names.stream().sorted(Comparator.comparingInt(String::length)).toList(); // niemutowalna
        System.out.println(sortedList);

        breakLine();

        // 5. Posortuj listę stringów malejąco według długości.
        System.out.println("Posortuj listę stringów malejąco według długości");
        List<String> descendingLengthOrder = names.stream().sorted(Comparator.comparingInt(String::length).reversed()).toList();
        System.out.println(descendingLengthOrder);

        breakLine();

        // 6. Posortuj ludzi rosnąco według wieku.
        System.out.println("Posortuj ludzi rosnąco według wieku");
        List<Person> peopleSortedByAgeAscending = people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .toList();
        peopleSortedByAgeAscending.forEach(person -> System.out.println(person.getName() + " age: " + person.getAge()));

        breakLine();

        // 7. Posortuj ludzi alfabetycznie według imienia.
        System.out.println("Posortuj ludzi alfabetycznie według imienia");
        List<Person> peopleSortedAlphabetically = people.stream()
                .sorted(Comparator.comparing(Person::getName)).toList();

        peopleSortedAlphabetically.forEach(person -> System.out.println(person.getName() + " age: " + person.getAge()));

        breakLine();

        // 8. Posortuj ludzi najpierw według wieku, a jeśli wiek równy — alfabetycznie według imienia.
        System.out.println("Posortuj ludzi najpierw według wieku, a jeśli wiek równy — alfabetycznie według imienia");
        List<Person> peopleSortedByAgeAndAlphabeticallyIfAgeEqual = people.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getName)).toList();

        peopleSortedByAgeAndAlphabeticallyIfAgeEqual.forEach(person -> System.out.println(person.getName() + " age: " + person.getAge()));

        breakLine();

        // 9. Posortuj ludzi malejąco według wieku, a jeśli wiek równy — odwrotnie alfabetycznie według imienia.
        System.out.println("Posortuj ludzi malejąco według wieku, a jeśli wiek równy — odwrotnie alfabetycznie według imienia");
        List<Person> peopleSortedByAgeDescendingAndAlphabeticallyIfAgeEqual = people.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed()
                        .thenComparing(Person::getName).reversed()).toList();

        peopleSortedByAgeDescendingAndAlphabeticallyIfAgeEqual.forEach(
                person -> System.out.println(person.getName() + " age: " + person.getAge())
        );

        breakLine();

        // 10. Posortuj tak, aby null-e były zawsze na końcu, a reszta alfabetycznie.
        List<String> sortedAlphabeticallyAndNullsAtTheEnd = stringWithNull.stream()
                .sorted((Comparator.nullsLast(String::compareTo))).toList();

        System.out.println(sortedAlphabeticallyAndNullsAtTheEnd);

        breakLine();
        // 11. Posortuj produkty w następującej kolejności:
        // Najpierw po rating malejąco (null-e zawsze na końcu),
        // potem po price rosnąco,
        // a jeśli rating i price równe, to alfabetycznie po nazwie.

        List<Product> sortedProducts = products.stream()
                .sorted(Comparator.comparing(
                                Product::getRating,
                                Comparator.nullsLast(Comparator.reverseOrder())
                        )
                        .thenComparing(Product::getPrice)
                        .thenComparing(Product::getName))
                .collect(Collectors.toList()); // mutowalna tablica

        sortedProducts.forEach(
                product -> System.out.println(product.getName() +
                        " price: " + product.getPrice() +
                        " rating: " + product.getRating()));
    }


    private void breakLine() {
        System.out.print("\n");
        System.out.println("*****************************************");
    }
}
