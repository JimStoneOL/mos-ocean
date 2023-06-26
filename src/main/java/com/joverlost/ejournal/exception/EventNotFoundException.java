package com.joverlost.ejournal.exception;

public class EventNotFoundException extends RuntimeException{
    public EventNotFoundException() {
        super("Представление не найдено");
    }
}
