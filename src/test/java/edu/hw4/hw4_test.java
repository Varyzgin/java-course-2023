package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class hw4_test {
    List<Animal> animals = List.of(
        new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 25, 5, true),
        new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 4, 30, 10, true),
        new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, false),
        new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 2, 5, 0, false),
        new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true),
        new Animal("Another Cat", Animal.Type.CAT, Animal.Sex.F, 2, 23, 4, true),
        new Animal("Another Bird", Animal.Type.BIRD, Animal.Sex.M, 1, 8, 1, false)
    );
    @Test
    @DisplayName("Сортировка по росту")
    void height_sort() {
        List<Animal> sortedAnimals = animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .collect(Collectors.toList());

        sortedAnimals.forEach(animal -> System.out.println(animal.name() + " - Height: " + animal.height()));
    }
    @Test
    @DisplayName("Сортировка по весу")
    void weight_sort() {
        List<Animal> sortedAnimals = animals.stream()
            .sorted(Comparator.comparingInt((Animal::weight)))
            .collect(Collectors.toList());

        sortedAnimals.forEach((animal -> System.out.println(animal.name() + " - Weight: " + animal.weight())));
    }
    @Test
    @DisplayName("Вычисление животных по типам")
    void animal_count_by_type() {
        Map<Animal.Type, Integer> animalCountByType = animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(animal -> 1)));

        for (Map.Entry<Animal.Type, Integer> entry : animalCountByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    @Test
    @DisplayName("Самое длинное имя")
    void longest_name() {
        Animal longestNameAnimal = animals.stream()
            .max((a1, a2) -> Integer.compare(a1.name().length(), a2.name().length()))
            .orElse(null);

        System.out.println(longestNameAnimal.name());
    }
    @Test
    @DisplayName("Более популярный пол")
    void most_popular_sex() {
        Map<Animal.Sex, Long> genderCounts = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));

        long maleCount = genderCounts.getOrDefault(Animal.Sex.M, 0L);
        long femaleCount = genderCounts.getOrDefault(Animal.Sex.F, 0L);

        System.out.println("Количество самцов: " + maleCount);
        System.out.println("Количество самок: " + femaleCount);

        if (maleCount > femaleCount) {
            System.out.println("Самцы популярнее");
        } else if (maleCount < femaleCount) {
            System.out.println("Самки популярнее");
        } else {
            System.out.println("Одинаково");
        }
    }
}
