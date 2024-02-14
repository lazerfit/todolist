package com.example.todolist.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.todolist.dtos.TodoUpdateDTO;
import com.example.todolist.domain.Todos;
import com.example.todolist.repository.TodosRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TodoServiceTest {

    @Autowired
    private TodosRepository todosRepository;

    @BeforeEach
    void setUp(){
        todosRepository.save(
            Todos.builder()
                .content("todo 앱 만들기11")
                .isDone(false)
                .build()
        );
    }

    @AfterEach
    void tearDown() {
        todosRepository.deleteAll();
    }

    @Test
    void save() {
        Todos todos=Todos.builder()
            .content("todo 앱 만들기")
            .isDone(false)
            .build();


        todosRepository.save(todos);
        Todos todos1 = todosRepository.findAll().get(1);

        assertThat(todos1.getIsDone()).isFalse();
        assertThat(todos1.getContent()).isEqualTo("todo 앱 만들기");
    }

    @Test
    void delete() {
        Todos todos = todosRepository.findAll().get(0);
        todosRepository.delete(todos);

        List<Todos> all = todosRepository.findAll();
        assertThat(all).isEmpty();
    }

    @Test
    void getOne() {
        Todos todos = todosRepository.findAll().get(0);
        assertThat(todos.getContent()).isEqualTo("todo 앱 만들기11");
        assertThat(todos.getIsDone()).isFalse();
    }

    @Test
    void getAll() {
        Todos todos=Todos.builder()
            .content("todo 앱 만들기")
            .isDone(false)
            .build();

        todosRepository.save(todos);

        List<Todos> all = todosRepository.findAll();

        assertThat(all).hasSize(2);
    }

    @Test
    @Transactional
    void update() {
        Todos todos = todosRepository.findAll().get(0);

        TodoUpdateDTO updateDTO = TodoUpdateDTO.builder()
            .content("수정")
            .isDone(true)
            .id(todos.getId())
            .build();

        todos.update(updateDTO);

        Todos todos1 = todosRepository.findAll().get(0);

        assertThat(todos1.getContent()).isEqualTo("수정");
        assertThat(todos1.getIsDone()).isTrue();

    }
}
