package edu.hw4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("HideUtilityClassConstructor")
public class AnimalValidator {
    @SuppressWarnings("MagicNumber")
    public static Map<String, Set<ValidationError>> validateAnimals(List<Animal> animals) {
        Map<String, Set<ValidationError>> errorMap = new HashMap<>();

        for (Animal animal : animals) {
            Set<ValidationError> errors = new HashSet<>();
            if (animal.weight() < 0) {
                errors.add(new ValidationError("Incorrect weight"));
            }
            if (animal.height() < 0) {
                errors.add(new ValidationError("Incorrect height"));
            }
            if (animal.age() < 0) {
                errors.add(new ValidationError("Incorrect age"));
            }
            if (animal.type() == Animal.Type.BIRD && animal.bites()) {
                errors.add(new ValidationError("Birds can't bit"));
            }
            if (animal.type() == Animal.Type.SPIDER && animal.weight() > 3) {
                errors.add(new ValidationError("Too much weight of spider"));
            }

            if (!errors.isEmpty()) {
                errorMap.put(animal.name(), errors);
            }
        }
        return errorMap;
    }
}
