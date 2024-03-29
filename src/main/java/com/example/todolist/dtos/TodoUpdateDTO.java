package com.example.todolist.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class TodoUpdateDTO {

    @NonNull
    private Long id;
    @NonNull
    private String content;

    @Builder
    public TodoUpdateDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
