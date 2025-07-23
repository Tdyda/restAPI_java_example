package com.example.restapi_java.service.ex;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class Ex1Console {
    List<String> names = Arrays.asList("Anna", "Jan", "Krzysztof", "Agnieszka", "Piotr", "Jan");

    public void print() {
        // 1. Wypisz wszystkie imiona
        System.out.println("1. Wypisz wszystkie imiona");
        names.forEach(System.out::println);
        breakLine();

        // 2. Usun duplikaty
        System.out.println("2. Usuń duplikaty");
        names.stream().distinct().forEach(System.out::println);
        breakLine();

        // 3. Posortuj alfabetycznie
        System.out.println("3. Posortuj alfabetycznie");
        names.stream().sorted().forEach(System.out::println);
        breakLine();

        // 4. Zlicz ile imion zaczyna się na literę "A"
        System.out.println("4. Zlicz ile imion zaczyna się na literę \"A\"");
        var count = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println(count);
        breakLine();

        // 5. Zrób listę imion z małych liter
        System.out.println("Zrób listę imion z małych liter");
        names.forEach(name -> System.out.println(name.toLowerCase()));
        breakLine();

        // 6. Policz, ile jest unikalnych imion o długości > 4
        System.out.println("Policz, ile jest unikalnych imion o długości > 4");
        Long size = names.stream().filter(name -> name.length() > 4)
                .distinct()
                .count();
        System.out.println(size);
        breakLine();

        // 7. Stwórz Mapę: pierwsza litera → lista imion na tę literę
        System.out.println("Stwórz Mapę: pierwsza litera → lista imion na tę literę");
        Map<String, List<String>> grouped = names.stream()
                .collect(Collectors.groupingBy(name -> name.substring(0, 1).toUpperCase()));

        grouped.forEach((letter, list) -> System.out.println(letter + " → " + list));
        breakLine();

        // 8. Znajdź najdłuższe imię (najwięcej znaków)
        System.out.println("Znajdź najdłuższe imię (najwięcej znaków)");
        var max = names.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("Brak imion");
        System.out.println(max);
        breakLine();
    }

    private void breakLine() {
        System.out.print("\n");
        System.out.println("*****************************************");
    }
}
