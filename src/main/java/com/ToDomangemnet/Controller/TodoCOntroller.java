package com.ToDomangemnet.Controller;


import com.ToDomangemnet.Service.TODOService;
import com.ToDomangemnet.TodoDTO.ToDoDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoCOntroller {

    private TODOService todoService;

    // Endpoint to create a new TODO item
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ToDoDTO> createToDo(@RequestBody ToDoDTO toDoDTO) {
        ToDoDTO createdToDo = todoService.addToDo(toDoDTO);
        return new ResponseEntity<>(createdToDo, HttpStatus.CREATED);
    }

    // Endpoint to get a TODO item by ID
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ToDoDTO> getToDoById(@PathVariable Long id) {
        ToDoDTO toDoDTO = todoService.getToDoById(id);
        return new ResponseEntity<>(toDoDTO, HttpStatus.OK);
    }

    // Endpoint to get all TODO items
    //http://localhost:8080/api/todos
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<ToDoDTO>> getAllToDos() {
        List<ToDoDTO> toDoDTOs = todoService.getAllTODOs();
        return new ResponseEntity<>(toDoDTOs, HttpStatus.OK);
    }

    // Endpoint to update a TODO item
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ToDoDTO> updateToDo(@PathVariable Long id, @RequestBody ToDoDTO toDoDTO) {
        ToDoDTO updatedToDo = todoService.updateToDo(id, toDoDTO);
        return new ResponseEntity<>(updatedToDo, HttpStatus.OK);
    }

    // Endpoint to delete a TODO item
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        todoService.deleteToDo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to mark a TODO item as completed
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}/complete")
    public ResponseEntity<ToDoDTO> completeToDo(@PathVariable Long id) {
        ToDoDTO completedToDo = todoService.CompleteToDo(id);
        return new ResponseEntity<>(completedToDo, HttpStatus.OK);
    }

    // Endpoint to mark a TODO item as incomplete
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<ToDoDTO> incompleteToDo(@PathVariable Long id) {
        ToDoDTO incompleteToDo = todoService.IncompleteToDo(id);
        return new ResponseEntity<>(incompleteToDo, HttpStatus.OK);
    }




}
