package com.example.restapi_java.service.ex;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Ex4Console {
    List<User> users = Arrays.asList(
            new User("Tomasz", 28, "ADMIN", true),
            new User("Anna", 28, "ADMIN", true),
            new User("Jan", 34, "USER", true),
            new User("Krzysztof", 45, "USER", false),
            new User("Agnieszka", 30, "GUEST", false),
            new User("Piotr", 29, "USER", true),
            new User("Paulina", 26, "ADMIN", false)
    );

    public void print() {
        // 1. Wypisz listę aktywnych użytkowników (active == true)
        System.out.println("Wypisz listę aktywnych użytkowników (active == true)");
        users.stream().filter(User::isActive).forEach(System.out::println);

        breakLine();

        // 2. Zrób listę imion tylko tych użytkowników, którzy mają rolę „ADMIN”
        System.out.println("Zrób listę imion tylko tych użytkowników, którzy mają rolę „ADMIN\"");
        users.stream().filter(u -> u.getRole().equals("ADMIN")).forEach(System.out::println);
        breakLine();

        // 3. Policz ilu jest użytkowników w każdej roli (np. ADMIN → 2, USER → 3, GUEST → 1)
        System.out.println("Policz ilu jest użytkowników w każdej roli (np. ADMIN → 2, USER → 3, GUEST → 1)");
        Map<String, Long> usersByRoleMap = users.stream()
                .collect(Collectors.groupingBy(
                        User::getRole,
                        Collectors.counting()
                ));

        usersByRoleMap.forEach((key, value) -> System.out.println(key + " - " + value));

        breakLine();

        // 4. Oblicz średni wiek aktywnych użytkowników
        System.out.println("Oblicz średni wiek aktywnych użytkowników");
        IntSummaryStatistics stats = users.stream().filter(User::isActive)
                .collect(Collectors.summarizingInt(User::getAge));

        System.out.println(stats.getAverage());

        breakLine();

        // 5.  Zbuduj mapę: rola → lista imion
        System.out.println(" Zbuduj mapę: rola → lista imion");
        Map<String, List<String>> mapOfRoleWithNames = users.stream()
                .collect(Collectors.groupingBy(User::getRole, Collectors.mapping(User::getName, Collectors.toList())));
        mapOfRoleWithNames.forEach((key, value) -> System.out.println(key + " - " + value));

        breakLine();

        // 6. Posortuj użytkowników malejąco po wieku, a jeśli wiek równy — alfabetycznie po imieniu
        System.out.println("Posortuj użytkowników malejąco po wieku, a jeśli wiek równy — alfabetycznie po imieniu");
        List<User> usersSortedByAgeAndAlphabeticallyIfAgeIsEqual = users.stream()
                .sorted(Comparator.comparing(User::getAge).reversed()
                        .thenComparing((User::getName)))
                .toList();

        breakLine();


        // 7. Czy są jacyś nieaktywni ADMINI? Jeśli tak, wypisz ich.
        System.out.println("Czy są jacyś nieaktywni ADMINI? Jeśli tak, wypisz ich");
        List<User> inActiveAdmins = users.stream()
                        .forEach(User::isActive);
        inActiveAdmins.forEach(user ->  System.out.println(user.getName()));
    }

    private void breakLine() {
        System.out.print("\n");
        System.out.println("*****************************************");
    }
}
