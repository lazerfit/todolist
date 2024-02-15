package com.example.todolist.dtos;

import com.example.todolist.domain.Todos;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TodoDTO {

    @NonNull
    private String content;
    private Boolean isDone;

    @Builder
    public TodoDTO(String content, Boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public TodoDTO(Todos todos) {
        this.content=todos.getContent();
        this.isDone=todos.getIsDone();
    }
}
