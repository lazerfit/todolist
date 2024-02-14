package com.example.todolist.exception;

public class TodoNotFound extends BaseException{

    private static final String MESSAGE = "존재하지 않는 글입니다.";

    public TodoNotFound() {
        super(MESSAGE);
    }
}
