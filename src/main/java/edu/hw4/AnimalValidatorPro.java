package edu.hw4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnimalValidatorPro {
    public static Map<String, String> validateAnimals(List<Animal> animals) {
        Map<String, String> errorMap = new HashMap<>();

        for (Animal animal : animals) {
            Set<ValidationErrorPro> errors = new HashSet<>();
            if (animal.weight() < 0) {
                errors.add(new ValidationErrorPro("Incorrect weight", animal, "weight"));
            }
            if (animal.height() < 0) {
                errors.add(new ValidationErrorPro("Incorrect height", animal, "height"));
            }
            if (animal.age() < 0) {
                errors.add(new ValidationErrorPro("Incorrect age", animal, "age"));
            }
            if (animal.type() == Animal.Type.BIRD && animal.bites()) {
                errors.add(new ValidationErrorPro("Birds can't bit", animal, "bites"));
            }
            if (animal.type() == Animal.Type.SPIDER && animal.weight() > 3) {
                errors.add(new ValidationErrorPro("Too much weight of spider", animal, "bites"));
            }

            if (!errors.isEmpty()) {
                StringBuilder str = new StringBuilder("Ошибка: ");
                for(var e : errors){
                    str.append("у животного ").append(e.animal().name())
                        .append(" в поле ").append(e.field())
                        .append(" - '").append(e.errorDescription()).append("'");
                }
                errorMap.put(animal.name(), str.toString());
            }
        }
        return errorMap;
    }
}
