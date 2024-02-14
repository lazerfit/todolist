package com.example.todolist.controller;

import com.example.todolist.dtos.TodoDTO;
import com.example.todolist.dtos.TodoUpdateDTO;
import com.example.todolist.domain.Todos;
import com.example.todolist.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/api/todo/save")
    public void save(@RequestBody Todos todos) {
        todoService.save(todos);
    }

    @PostMapping("/api/todo/delete/{id}")
    public void delete(@PathVariable Long id) {
        todoService.delete(id);
    }

    @GetMapping("/api/todo/{id}")
    public TodoDTO getTodo(@PathVariable Long id) {
        return todoService.getTodo(id);
    }

    @GetMapping("/api/todo/all")
    public List<TodoDTO> getAll() {
        return todoService.getAllTodos();
    }

    @PostMapping("/api/todo/update")
    public TodoDTO update(@RequestBody TodoUpdateDTO updateDTO) {
        return todoService.update(updateDTO);
    }
}
