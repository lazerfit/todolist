package com.example.todolist.dtos;

import com.example.todolist.domain.Todos;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoResponseDTO {

    private Long id;
    private String content;
    private Boolean isDone;

    @Builder
    public TodoResponseDTO(Long id, String content, Boolean isDone) {
        this.id = id;
        this.content = content;
        this.isDone = isDone;
    }

    public TodoResponseDTO(Todos todos) {
        this.id = todos.getId();
        this.content = todos.getContent();
        this.isDone = todos.getIsDone();
    }
}
