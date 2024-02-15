package com.example.todolist.service;

import com.example.todolist.domain.Todos;
import com.example.todolist.dtos.CheckedToggleUpdate;
import com.example.todolist.dtos.TodoDTO;
import com.example.todolist.dtos.TodoResponseDTO;
import com.example.todolist.dtos.TodoUpdateDTO;
import com.example.todolist.exception.TodoNotFound;
import com.example.todolist.repository.TodosRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodosRepository todosRepository;

    @Transactional
    public TodoResponseDTO save(TodoDTO todo) {
        Todos savedTodos = todosRepository.save(
            Todos.builder().content(todo.getContent()).isDone(todo.getIsDone()).build());
        return new TodoResponseDTO(savedTodos);
    }

    @Transactional
    public void delete(Long id){
        Todos todos = todosRepository.findById(id).orElseThrow(TodoNotFound::new);
        todosRepository.delete(todos);
    }

    @Transactional
    public void deleteAll() {
        todosRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    public List<TodoResponseDTO> getAllTodos() {
        List<Todos> all = todosRepository.findAll();
        return all.stream().map(TodoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public TodoResponseDTO getTodo(Long id) {
        Todos todos = todosRepository.findById(id).orElseThrow(TodoNotFound::new);
        return new TodoResponseDTO(todos);
    }

    @Transactional
    public TodoResponseDTO update(TodoUpdateDTO updateDTO) {
        Todos todos = todosRepository.findById(updateDTO.getId()).orElseThrow(TodoNotFound::new);
        todos.update(updateDTO);
        return new TodoResponseDTO(todos);
    }

    @Transactional
    public TodoResponseDTO checkedToggleUpdate(CheckedToggleUpdate update) {
        Todos todos = todosRepository.findById(update.getId()).orElseThrow(TodoNotFound::new);
        todos.checkedToggleUpdate(update);
        return new TodoResponseDTO(todos);
    }
}
