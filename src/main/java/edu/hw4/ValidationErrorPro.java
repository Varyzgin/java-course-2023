package edu.hw4;

public record ValidationErrorPro(String errorDescription, Animal animal, AnimalValidatorPro.Field field) {
}
