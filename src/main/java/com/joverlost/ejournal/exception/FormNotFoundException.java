package com.joverlost.ejournal.exception;

public class FormNotFoundException extends RuntimeException{
    public FormNotFoundException() {
        super("Анкета не найдена");
    }
}
