package com.example.todolist.service;

import com.example.todolist.dtos.TodoDTO;
import com.example.todolist.dtos.TodoUpdateDTO;
import com.example.todolist.domain.Todos;
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
    public void save(Todos todo) {
        todosRepository.save(todo);
    }

    @Transactional
    public void delete(Long id){
        Todos todos = todosRepository.findById(id).orElseThrow(TodoNotFound::new);
        todosRepository.delete(todos);
    }

    @Transactional(readOnly = true)
    public List<TodoDTO> getAllTodos() {
        List<Todos> all = todosRepository.findAll();
        return all.stream().map(TodoDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public TodoDTO getTodo(Long id) {
        Todos todos = todosRepository.findById(id).orElseThrow(TodoNotFound::new);
        return new TodoDTO(todos);
    }

    @Transactional
    public TodoDTO update(TodoUpdateDTO updateDTO) {
        Todos todos = todosRepository.findById(updateDTO.getId()).orElseThrow(TodoNotFound::new);
        todos.update(updateDTO);
        return new TodoDTO(todos);
    }
}
