package com.example.todolist.domain;

import com.example.todolist.dtos.TodoUpdateDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Todos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Column
    private Boolean isDone;

    @Builder
    public Todos(String content, Boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    public void update(TodoUpdateDTO updateDTO) {
        this.content= updateDTO.getContent();
        this.isDone=updateDTO.getIsDone();
    }
}
