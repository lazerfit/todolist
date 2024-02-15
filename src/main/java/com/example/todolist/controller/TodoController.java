package com.example.todolist.controller;

import com.example.todolist.dtos.CheckedToggleUpdate;
import com.example.todolist.dtos.TodoDTO;
import com.example.todolist.dtos.TodoResponseDTO;
import com.example.todolist.dtos.TodoUpdateDTO;
import com.example.todolist.service.TodoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = "http://localhost:5173", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/api/todo/save")
    public TodoResponseDTO save(@Validated @RequestBody TodoDTO todos) {
        log.info("저장 컨트롤러 호출");
        return todoService.save(todos);
    }

    @PostMapping("/api/todo/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        log.info("삭제 컨트롤러 호출");
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
    public TodoResponseDTO update(@Validated @RequestBody TodoUpdateDTO updateDTO) {
        return todoService.update(updateDTO);
    }

    @PostMapping("/api/todo/checked")
    public TodoResponseDTO toggleUpdate(@Validated @RequestBody CheckedToggleUpdate update) {
        return todoService.checkedToggleUpdate(update);
    }
}
