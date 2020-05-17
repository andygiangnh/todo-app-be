package com.mytodo.controller;

import com.mytodo.model.Todo;
import com.mytodo.model.User;
import com.mytodo.repository.TodoRepository;
import com.mytodo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/todos")
public class TodoResource {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Todo>> findAll(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Error: user not found!"));

        return  ResponseEntity.ok(todoRepository.findByUserId(user.getId()));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Error: user not found!"));
        todo.setUserId(user.getId());
        todoRepository.saveAndFlush(todo);
        try {
            Thread.sleep(2000);
        } catch(Exception ex) {}
        return ResponseEntity.ok(todo);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo updateTodo) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todo.setCompleted(updateTodo.isCompleted());
                    todoRepository.saveAndFlush(todo);
                    return new ResponseEntity<>(todo, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Integer> deleteTodo(@PathVariable Integer id) {
        return todoRepository.findById(id)
                .map(todo -> {
                    todoRepository.delete(todo);
                    return ResponseEntity.ok(id);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
