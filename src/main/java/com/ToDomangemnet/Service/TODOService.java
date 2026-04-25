package com.ToDomangemnet.Service;

import com.ToDomangemnet.TodoDTO.ToDoDTO;

import java.util.List;

public interface TODOService {

    ToDoDTO addToDo(ToDoDTO toDoDTO);

    ToDoDTO getToDoById(Long id);

    List<ToDoDTO> getAllTODOs();

    ToDoDTO updateToDo(Long id, ToDoDTO toDoDTO);

    void deleteToDo(Long id);

    ToDoDTO CompleteToDo(Long id);

    ToDoDTO IncompleteToDo(Long id);





}
