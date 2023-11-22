package edu.hw4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnimalsTest {

    List<Animal> animals = List.of(
        new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 25, 5, true),
        new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 4, 110, 10, true),
        new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, true),
        new Animal("Fish", Animal.Type.FISH, Animal.Sex.F, 2, 5, 0, false),
        new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true),
        new Animal("Another Cat", Animal.Type.CAT, Animal.Sex.F, 2, 23, 4, true),
        new Animal("Another Bird", Animal.Type.BIRD, Animal.Sex.M, 1, 8, 1, false)
    );
    List<Animal> animals1 = List.of(
        new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 3, 25, 5, true),
        new Animal("Fish2", Animal.Type.FISH, Animal.Sex.M, 4, 110, 10, true),
        new Animal("Fish3", Animal.Type.FISH, Animal.Sex.F, 2, 5, 0, false),
        new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 1, 1, 0, true),
        new Animal("Another Cat", Animal.Type.CAT, Animal.Sex.F, 2, 23, 4, true),
        new Animal("Another Bird", Animal.Type.BIRD, Animal.Sex.M, 1, 8, 1, false)
    );
    List<Animal> animals2 = List.of(
        new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, 3, 25, 5, true),
        new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 4, 110, 10, true),
        new Animal("Bird", Animal.Type.BIRD, Animal.Sex.F, 1, 10, 1, false),
        new Animal("Fish4", Animal.Type.FISH, Animal.Sex.M, 1, 1, 0, true),
        new Animal("Fish5", Animal.Type.FISH, Animal.Sex.F, 2, 23, 4, true),
        new Animal("Fish6", Animal.Type.FISH, Animal.Sex.M, 1, 8, 1, false)
    );

    @Test
    @DisplayName("1. Сортировка по росту")
    void height_sort() {
        List<Animal> sortedAnimals = animals.stream()
            .sorted(comparingInt(Animal::height))
            .toList();

//        sortedAnimals.forEach(animal -> System.out.println(animal.name() + " - Height: " + animal.height()));
        assertEquals(1, sortedAnimals.get(0).height());
        assertEquals(110, sortedAnimals.get(6).height());
    }

    @Test
    @DisplayName("2. Сортировка по весу")
    void weight_sort() {
        int k = 4;
        List<Animal> sortedAnimals = animals
            .subList(0, Math.min(k, animals.size()))
            .stream()
            .sorted(comparingInt(Animal::weight))
            .toList();

//        sortedAnimals.forEach(animal -> System.out.println(animal.name() + " - Weight: " + animal.weight()));
        assertEquals(0, sortedAnimals.get(0).weight());
        assertEquals(10, sortedAnimals.get(k - 1).weight());
    }

    @Test
    @DisplayName("3. Сколько животных каждого вида")
    void animal_count_by_type() {
        Map<Animal.Type, Long> animalCountByType = animals.stream()
            .collect(groupingBy(Animal::type, counting()));

        for (var entry : animalCountByType.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        assertEquals(2, animalCountByType.get(Animal.Type.CAT));
        assertEquals(1, animalCountByType.get(Animal.Type.SPIDER));
    }

    @Test
    @DisplayName("4. Самое длинное имя")
    void longest_name() {
        Animal longestNameAnimal = animals.stream()
            .max((a1, a2) -> Integer.compare(a1.name().length(), a2.name().length()))
            .orElse(null);

        assert longestNameAnimal != null;
        assertEquals("Another Bird", longestNameAnimal.name());
    }

    @Test
    @DisplayName("5. Каких животных больше")
    void most_popular_sex() {
        Map<Animal.Sex, Long> genderCounts = animals.stream()
            .collect(groupingBy(Animal::sex, counting()));

        long maleCount = genderCounts.getOrDefault(Animal.Sex.M, 0L);
        long femaleCount = genderCounts.getOrDefault(Animal.Sex.F, 0L);

        Animal.Sex sex = null;

        if (maleCount > femaleCount) {
            sex = Animal.Sex.M;
        } else if (maleCount < femaleCount) {
            sex = Animal.Sex.F;
        }

        assertEquals(4, maleCount);
        assertEquals(3, femaleCount);
        assertSame(sex, Animal.Sex.M);
    }

    @Test
    @DisplayName("6. Самое тяжелое животное каждого вида")
    void biggest_weight_by_type() {
        Map<Animal.Type, Animal> biggestWeights = animals.stream()
            .collect(toMap(Animal::type, Function.identity(),
                BinaryOperator.maxBy(comparingInt(Animal::weight))
            ));

        assertEquals(5, biggestWeights.get(Animal.Type.CAT).weight());
        assertEquals(1, biggestWeights.get(Animal.Type.BIRD).weight());
    }

    @Test
    @DisplayName("7. Старейший")
    void k_oldest_animal() {
        int k = 3;
        Animal animal = animals.stream()
            .sorted(comparingInt(Animal::age).reversed())
            .skip(k - 1).findFirst().orElse(null);

        assertTrue("Fish".equals(animal.name()) || "Another Cat".equals(animal.name()));
    }

    @Test
    @DisplayName("8. Тяжелейший из нижайших")
    void biggest_in_smallest() {
        Optional<Animal> animal = animals.stream()
            .filter(e -> e.height() < 30)
            .max(comparingInt(Animal::weight));

        assertEquals(5, animal.map(Animal::weight).orElse(0));
    }

    @Test
    @DisplayName("9. Сумма лап")
    void sum_of_paws() {
        Integer paws = 0;
        for (var e : animals) {
            paws += e.paws();
        }

        assertEquals(24, paws);
    }

    @Test
    @DisplayName("10. Возраст != лапы")
    void age_is_not_equal_paws_count() {
        List<Animal> animalSet = animals.stream()
            .collect(filtering(e -> e.age() != e.paws(), toList()));

        assertEquals(6, animalSet.size());
    }

    @Test
    @DisplayName("11. Большие и кусачие")
    void big_and_biting() {
        List<Animal> bigAndBits = animals.stream()
            .collect(filtering(e -> e.height() > 100 && e.bites(), toList()));

        assertEquals(1, bigAndBits.size());
    }

    @Test
    @DisplayName("12. Тяжелее роста?")
    void higher_than_weight() {
        Integer count = animals.stream()
            .collect(filtering(e -> e.weight() > e.height(), toList()))
            .size();

        assertEquals(0, count);
    }

    @Test
    @DisplayName("13. Ваши величества")
    void two_and_above_names() {
        List<Animal> persons = animals.stream()
            .collect(filtering(e -> e.name().split(" ").length > 1, toList()));

        assertEquals(2, persons.size());
    }

    @Test
    @DisplayName("14. Большая собака")
    void big_dog() {
        int k = 100;
        boolean contains = false;
        for (var animal : animals) {
            if (animal.height() > k && animal.type() == Animal.Type.DOG) {
                contains = true;
                break;
            }
        }

        assertTrue(contains);
    }

    @Test
    @DisplayName("15. Минимальный вес у подростков")
    void min_weight_in_diapason() {
        int k = 3, l = 6;
        Map<Animal.Type, Integer> lighest = animals.stream()
            .filter(e -> e.weight() > k && e.weight() < l)
            .collect(groupingBy(Animal::type, summingInt(Animal::weight)));

        assertEquals(9, lighest.get(Animal.Type.CAT));
        assertNull(lighest.get(Animal.Type.BIRD));
    }

    @Test
    @DisplayName("16. Куча сортировок")
    void triple_sorted_animal_list() {
        List<Animal> list = animals.stream()
            .sorted(comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name)).toList();

        assertTrue(list.contains(animals.get(0)));
    }

    @Test
    @DisplayName("17. Пауки кусаются чаще собак?")
    void spiders_and_dogs_compare() {
        boolean spiders = false;
        Map<Animal.Type, Long> biteCounts = animals.stream()
            .filter(e -> e.bites() && (e.type() == Animal.Type.DOG || e.type() == Animal.Type.SPIDER))
            .collect(groupingBy(Animal::type, counting()));
        if (biteCounts.get(Animal.Type.SPIDER) > biteCounts.get(Animal.Type.DOG)) {
            spiders = true;
        }

        assertFalse(spiders);
    }

    @Test
    @DisplayName("18. Самая тяжелая рыба")
    void thickest_fish() {
        boolean spiders = false;
        List<Animal> candidates = List.of(
            Objects.requireNonNull(animals.stream()
                .filter(e -> e.type() == Animal.Type.FISH)
                .max(comparingInt(Animal::weight)).orElse(null)),
            Objects.requireNonNull(animals1.stream()
                .filter(e -> e.type() == Animal.Type.FISH)
                .max(comparingInt(Animal::weight)).orElse(null)),
            Objects.requireNonNull(animals2.stream()
                .filter(e -> e.type() == Animal.Type.FISH)
                .max(comparingInt(Animal::weight)).orElse(null))
        );
        Animal winner = candidates.stream()
                .max(comparingInt(Animal::weight)).orElse(null);

        assertEquals(10, winner.weight());
    }

    @Test
    @DisplayName("19. Чекер ошибок")
    void errors_checker() {
        Map<String, Set<ValidationError>> errors = AnimalValidator.validateAnimals(animals);

        assertTrue(errors.get("Bird").stream().anyMatch(error -> error.errorDescription().equals("Birds can't bit")));
    }

    @Test
    @DisplayName("20. Чекер ошибок PRO")
    void errors_checker_pro() {
        Map<String, String> errors = AnimalValidatorPro.validateAnimals(animals);

        assertEquals("Ошибка: у животного Bird в поле BITES - 'Birds can't bit'", errors.get("Bird"));
    }
}
