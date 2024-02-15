package com.example.todolist.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class CheckedToggleUpdate {

    @NonNull
    private Long id;

    @NonNull
    private Boolean isDone;

    @Builder
    public CheckedToggleUpdate(@NonNull Long id, @NonNull Boolean isDone) {
        this.id = id;
        this.isDone = isDone;
    }
}
