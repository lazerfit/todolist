package com.example.todolist.controller;

import com.example.todolist.domain.Todos;
import com.example.todolist.dtos.TodoResponseDTO;
import com.example.todolist.dtos.TodoUpdateDTO;
import com.example.todolist.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/api/todo/save")
    public TodoResponseDTO save(@RequestBody Todos todos) {
        return todoService.save(todos);
    }

    @PostMapping("/api/todo/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        todoService.delete(id);
        return new ResponseEntity<>("Successfully delete "+id, HttpStatus.OK);
    }

    @PostMapping("/api/todo/deleteAll")
    public ResponseEntity<String> deleteAll() {
        todoService.deleteAll();
        return ResponseEntity.ok("Successfully delete all");
    }

    @GetMapping("/api/todo/{id}")
    public TodoResponseDTO getTodo(@PathVariable Long id) {
        return todoService.getTodo(id);
    }

    @GetMapping("/api/todo/all")
    public List<TodoResponseDTO> getAll() {
        return todoService.getAllTodos();
    }

    @PostMapping("/api/todo/update")
    public TodoResponseDTO update(@RequestBody TodoUpdateDTO updateDTO) {
        return todoService.update(updateDTO);
    }
}
