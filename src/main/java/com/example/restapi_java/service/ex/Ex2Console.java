package com.example.restapi_java.service.ex;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class Ex2Console {
    Map<String, Integer> nameToAge = Map.of(
            "Anna", 28,
            "Jan", 34,
            "Krzysztof", 45,
            "Agnieszka", 30,
            "Piotr", 29,
            "Paulina", 26
    );

    public void print() {
        // 1. Wypisz wszystkie pary imię → wiek.
        System.out.println("Wypisz wszystkie pary imię → wiek");
        nameToAge.forEach((name, age) -> {
            System.out.printf(name + ": %d\n", age);
        });
        breakLine();

        // 2. Znajdź najstarszą osobę.
        System.out.println("Znajdź najstarszą osobę");
        System.out.println(nameToAge.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow(IllegalStateException::new));
        breakLine();

        // 3. Wypisz osoby poniżej 30 lat.

        System.out.println("Wypisz osoby poniżej 30 lat");
        nameToAge.entrySet().stream()
                .filter(entry -> entry.getValue() < 30)
                .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        breakLine();

        // 4. Zrób listę samych imion (bez wieku)
        System.out.println("Zrób listę samych imion (bez wieku)");
        List<String> onlyNames = nameToAge.keySet().stream()
                .toList();
        onlyNames.forEach(System.out::println);
        breakLine();

        // 5. Zrób listę samych wieków (bez imion)
        System.out.println("Zrób listę samych wieków (bez imion)");
        List<Integer> onlyAge = nameToAge.values().stream().toList();
        onlyAge.forEach(System.out::println);
        breakLine();

        // 6. Oblicz sumę wszystkich lat (suma wieków)
        System.out.println("Oblicz sumę wszystkich lat (suma wieków)");
        int sum = nameToAge.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);
        breakLine();

        // 7.  Wypisz tylko osoby, które mają co najmniej 30 lat, posortowane malejąco po wieku.
        System.out.println("Wypisz tylko osoby, które mają co najmniej 30 lat, posortowane malejąco po wieku");
        nameToAge.entrySet().stream()
                .filter(entry -> entry.getValue() >= 30)
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
        breakLine();

        // 8. Zbuduj listę imion osób młodszych niż 40 lat, ale zapisz je wielkimi literami.
        System.out.println("Zbuduj listę imion osób młodszych niż 40 lat, ale zapisz je wielkimi literami");
        List<String> namesYoungerThanFourtyToUpper = nameToAge.entrySet().stream()
                .filter(entry -> entry.getValue() < 40)
                .map(entry -> entry.getKey().toUpperCase()).toList();
        namesYoungerThanFourtyToUpper.forEach(System.out::println);
        breakLine();

        // 9. Znajdź osobę, której imię jest najkrótsze, i wypisz jej wiek.
        System.out.println("Znajdź osobę, której imię jest najkrótsze, i wypisz jej wiek");
        System.out.println(nameToAge.entrySet().stream()
                .min(Comparator.comparingInt(e -> e.getKey().length()))
                .orElseThrow(IllegalStateException::new)
        );

        breakLine();

        // 10. Posortuj wpisy w mapie alfabetycznie po imieniu (kluczu) i wypisz w formacie: „Imię ma X lat”.
        System.out.println("Posortuj wpisy w mapie alfabetycznie po imieniu (kluczu) i wypisz w formacie: „Imię ma X lat”");
        nameToAge.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + " ma " + entry.getValue() + " lat"));

        // 11. Stwórz Mapę: długość imienia → lista osób z taką długością imienia.
        System.out.println("Stwórz Mapę: długość imienia → lista osób z taką długością imienia");
        Map<Integer, List<String>> map = nameToAge.keySet().stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toList()
                ));
        map.forEach((length, list) -> System.out.println(length + " → " + list));

        // 12. Policz, ile osób ma imię zaczynające się na literę „P”.
        System.out.println("Policz, ile osób ma imię zaczynające się na literę „P”");
        System.out.println(nameToAge.keySet().stream()
                .filter(key -> key.startsWith("P")).count());
        breakLine();

        // 13. Stwórz Set z unikalnych długości imion występujących w mapie.
        System.out.println("Stwórz Set z unikalnych długości imion występujących w mapie");
        Set<Integer> set = nameToAge.keySet().stream()
                .map(String::length)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);

        breakLine();

        // 14. Stwórz Map z unikalnych długości imion występujących w mapie.
        System.out.println("Stwórz Map z unikalnych długości imion występujących w mapie");
        Map<Integer, List<String>> map1 = nameToAge.keySet().stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toList()));
        map1.forEach((length, list) -> System.out.println(length + " " + list));
        breakLine();

        // 15. Stwórz Map z unikalnych długości imion występujących w mapie używając entrySet.
        System.out.println("Stwórz Map z unikalnych długości imion występujących w mapie używając entrySet");
        Map<Integer, List<String>> map2 = nameToAge.entrySet().stream()
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().length(),
                        Collectors.mapping(Map.Entry::getKey,
                                Collectors.toList())
                ));
        map2.forEach((length, list) -> System.out.println(length + " -> " + list));

        // 16. Oblicz, jaka jest największa różnica wieku między dwiema osobami w mapie.
        System.out.println("Oblicz, jaka jest największa różnica wieku między dwiema osobami w mapie");
        IntSummaryStatistics stats = nameToAge.values().stream()
                .collect(Collectors.summarizingInt(Integer::intValue));

        int diff = stats.getMax() - stats.getMin();
        System.out.println(diff);

        breakLine();

        // 17. Zbuduj Mapę: pierwsza litera imienia → średni wiek osób na tę literę
        System.out.println("Zbuduj Mapę: pierwsza litera imienia → średni wiek osób na tę literę");

        Map<String, Double> map3 = nameToAge.entrySet().stream()
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().substring(0, 1),
                        Collectors.averagingInt((Map.Entry::getValue))
                ));

        map3.forEach((length, list) -> System.out.println(length + " -> " + list));

        // 18. Zbuduj mapę: ostatnia litera imienia → suma wieku osób kończących się na tę literę.
        System.out.println("Zbuduj mapę: ostatnia litera imienia → suma wieku osób kończących się na tę literę");
        Map<String, Integer> map4 = nameToAge.entrySet().stream()
                .collect(Collectors.groupingBy(
                        entry -> entry.getKey().substring(entry.getKey().length() - 1).toUpperCase(),
                        Collectors.summingInt((Map.Entry::getValue))
                ));
        map4.forEach((length, list) -> System.out.println(length + " -> " + list));

        breakLine();

        // 19. Policz sumę wieków za pomocą reduce.
        System.out.println("Policz sumę wieków za pomocą reduce");
        int sum1 = nameToAge.values().stream().reduce(0, Integer::sum);
        System.out.println(sum1);

        breakLine();

        // 20. Zbuduj Stringa: „Anna, Jan, Krzysztof...” używając reduce.
        System.out.println("Zbuduj Stringa: „Anna, Jan, Krzysztof...” używając reduce");
        String names = nameToAge.keySet().stream().reduce("", (a, b) -> a + b + ", ");
        names = names.replaceAll(", $", "");
        System.out.println(names);

        breakLine();

        // 21. Znajdź największy wiek używając reduce.
        System.out.println(" Znajdź największy wiek używając reduce");
        int max = nameToAge.values().stream().reduce(0, (a, b) -> {
            if (b > a) {
                return b;
            }
            return a;
        });
        System.out.println(max);

        breakLine();

        // 22. Policz, ile liter mają wszystkie imiona razem (czyli sumę długości wszystkich kluczy) używając reduce.
        System.out.println("Policz, ile liter mają wszystkie imiona razem (czyli sumę długości wszystkich kluczy) używając reduce");
        int sum2 = nameToAge.keySet().stream().reduce(0, (acc, name) -> acc + name.length(), Integer::sum);
        System.out.println(sum2);

        breakLine();

        // 23. Zbuduj listę imion wielkimi literami, ale za pomocą reduce (czyli zbierając do listy ręcznie).
        System.out.println("Zbuduj listę imion wielkimi literami, ale za pomocą reduce (czyli zbierając do listy ręcznie)");
        List<String> names2 = nameToAge.keySet().stream()
                .reduce(new ArrayList<>(), (acc, name) -> {
                            acc.add(name.toUpperCase());
                            return acc;
                        },
                        (acc1, acc2) -> {
                            acc1.addAll(acc2);
                            return acc1;
                        });
        names2.forEach(System.out::println);
        breakLine();
    }

    private void breakLine() {
        System.out.print("\n");
        System.out.println("*****************************************");
    }
}
