package com.example.todolist.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.todolist.dtos.TodoDTO;
import com.example.todolist.dtos.TodoResponseDTO;
import com.example.todolist.dtos.TodoUpdateDTO;
import com.example.todolist.domain.Todos;
import com.example.todolist.repository.TodosRepository;
import com.example.todolist.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodosRepository todosRepository;

    @BeforeEach
    void tearDown() {
        todosRepository.deleteAll();
    }


    @Test
    void save() throws Exception {
        TodoDTO todos= TodoDTO.builder()
            .content("todo 앱 만들기")
            .isDone(false)
            .build();

        mockMvc.perform(post("/api/todo/save")
                .content(objectMapper.writeValueAsString(todos))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andDo(print());

        List<TodoResponseDTO> allTodos = todoService.getAllTodos();
        assertThat(allTodos).hasSize(1);
        assertThat(allTodos.get(0).getContent()).isEqualTo("todo 앱 만들기");
        assertThat(allTodos.get(0).getIsDone()).isFalse();
    }

    @Test
    void getOne() throws Exception{
        Todos saved = todosRepository.save(
            Todos.builder()
                .content("내용")
                .isDone(false)
                .build()
        );

        mockMvc.perform(get("/api/todo/"+saved.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").value("내용"))
            .andExpect(jsonPath("$.isDone").value(false))
            .andDo(print());

    }

    @Test
    void getAll() throws Exception{
        todosRepository.save(
            Todos.builder()
                .content("내용")
                .isDone(false)
                .build()
        );

        todosRepository.save(
            Todos.builder()
                .content("내용2")
                .isDone(true)
                .build()
        );

        mockMvc.perform(get("/api/todo/all"))
            .andExpectAll(status().isOk())
            .andExpect(jsonPath("$[0].content").value("내용"))
            .andExpect(jsonPath("$[1].content").value("내용2"))
            .andExpect(jsonPath("$[0].isDone").value(false))
            .andExpect(jsonPath("$[1].isDone").value(true))
            .andDo(print());
    }

    @Test
    void delete() throws Exception {
        Todos saved = todosRepository.save(
            Todos.builder()
                .content("내용")
                .isDone(false)
                .build()
        );

        mockMvc.perform(post("/api/todo/delete/"+ saved.getId()))
            .andExpect(status().isOk())
            .andDo(print());

        List<Todos> all = todosRepository.findAll();

        assertThat(all).isEmpty();
    }

    @Test
    void update() throws Exception {
        Todos saved = todosRepository.save(
            Todos.builder()
                .content("내용")
                .isDone(false)
                .build()
        );

        TodoUpdateDTO updateDTO = TodoUpdateDTO.builder()
            .content("수정")
            .isDone(true)
            .id(saved.getId())
            .build();

        mockMvc.perform(post("/api/todo/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").value("수정"))
            .andExpect(jsonPath("$.isDone").value(true))
            .andDo(print());

    }
}
