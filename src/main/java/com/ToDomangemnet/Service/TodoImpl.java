package com.ToDomangemnet.Service;

import com.ToDomangemnet.Entity.TODO;
import com.ToDomangemnet.Repository.ToDoRepository;
import com.ToDomangemnet.TodoDTO.ToDoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoImpl implements TODOService{

    private ToDoRepository toDoRepository;
    private ModelMapper modelMapper;


    @Override
    public ToDoDTO addToDo(ToDoDTO toDoDTO) {
         if(toDoDTO.getTitle() == null || toDoDTO.getTitle().isEmpty()){
            throw new IllegalArgumentException("Title cannot be null or empty");
         }
         if(toDoDTO.getDescription() == null || toDoDTO.getDescription().isEmpty()){
            throw new IllegalArgumentException("Description cannot be null or empty");
         }
         if(toDoDTO.isCompleted()){
            throw new IllegalArgumentException("Completed cannot be true when adding a new ToDo");
         }

         TODO todo = modelMapper.map(toDoDTO, TODO.class);
            TODO savedTodo = toDoRepository.save(todo);

            ToDoDTO savedToDoDTO = modelMapper.map(savedTodo, ToDoDTO.class);
            return savedToDoDTO;

    }

    @Override
    public ToDoDTO getToDoById(Long id) {
        if(id == null || id < 0){
            throw new IllegalArgumentException("ID cannot be null or negative");
        }
        TODO todo = toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo not found with ID: " + id));
        ToDoDTO toDoDTO = modelMapper.map(todo, ToDoDTO.class);
        return toDoDTO;
    }

    @Override
    public List<ToDoDTO> getAllTODOs() {
        List <TODO>  todo = toDoRepository.findAll();

                return todo.stream().map(t -> modelMapper.map(t, ToDoDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public ToDoDTO updateToDo(Long id, ToDoDTO toDoDTO) {
        if(id == null || id < 0){
            throw new IllegalArgumentException("ID cannot be null or negative");
        }
        TODO existingTodo = toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo not found with ID: " + id));

        if(toDoDTO.getTitle() != null && !toDoDTO.getTitle().isEmpty()){
            existingTodo.setTitle(toDoDTO.getTitle());
        }
        if(toDoDTO.getDescription() != null && !toDoDTO.getDescription().isEmpty()){
            existingTodo.setDescription(toDoDTO.getDescription());
        }
        existingTodo.setCompleted(toDoDTO.isCompleted());

        TODO updatedTodo = toDoRepository.save(existingTodo);
        ToDoDTO updatedToDoDTO = modelMapper.map(updatedTodo, ToDoDTO.class);
        return updatedToDoDTO;
    }

    @Override
    public void deleteToDo(Long id) {
        if(id == null || id < 0){
            throw new IllegalArgumentException("ID cannot be null or negative");
        }
        TODO existingTodo = toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo not found with ID: " + id));
        toDoRepository.delete(existingTodo);
    }

    @Override
    public ToDoDTO CompleteToDo(Long id) {

        TODO todo = toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo not found with ID: " + id));
        todo.setCompleted(true);
        TODO updatedTodo = toDoRepository.save(todo);
        ToDoDTO updatedToDoDTO = modelMapper.map(updatedTodo, ToDoDTO.class);
        return updatedToDoDTO;
    }

    @Override
    public ToDoDTO IncompleteToDo(Long id) {
        TODO todo = toDoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ToDo not found with ID: " + id));
        todo.setCompleted(false);
        TODO updatedTodo = toDoRepository.save(todo);
        ToDoDTO updatedToDoDTO = modelMapper.map(updatedTodo, ToDoDTO.class);
        return updatedToDoDTO;
    }
}
