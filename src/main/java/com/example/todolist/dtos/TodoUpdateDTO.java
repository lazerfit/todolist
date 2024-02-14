package com.example.todolist.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TodoUpdateDTO {

    private Long id;
    private String content;
    private Boolean isDone;

    @Builder
    public TodoUpdateDTO(Long id, String content, Boolean isDone) {
        this.id = id;
        this.content = content;
        this.isDone = isDone;
    }
}
