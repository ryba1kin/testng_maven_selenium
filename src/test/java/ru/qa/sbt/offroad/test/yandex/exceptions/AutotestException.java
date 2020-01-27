package ru.qa.sbt.offroad.test.yandex.exceptions;

public class AutotestException extends Exception {
    public AutotestException(String message) {
        super(message);
    }

    public AutotestException(String message, Throwable cause) {
        super(message, cause);
    }
}
