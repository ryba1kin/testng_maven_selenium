package ru.qa.sbt.offroad.test.yandex.core;

import java.util.function.Predicate;

public class FunctionalHelper {

    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }
}
