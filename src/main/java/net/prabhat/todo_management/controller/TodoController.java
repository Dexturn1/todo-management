package net.prabhat.todo_management.controller;


import lombok.AllArgsConstructor;
import net.prabhat.todo_management.dto.TodoDto;
import net.prabhat.todo_management.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private TodoService todoService;


    @PostMapping()
    public ResponseEntity<TodoDto> addTodo( @RequestBody TodoDto todoDto){
        TodoDto todo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.getTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> todos = todoService.getAllTodo();
        return new ResponseEntity<>(todos,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable Long id){
        TodoDto updateTodo = todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(updateTodo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return ResponseEntity.ok("Todo has Has been deleted Successfully! ");
    }


    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.completeTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    @PatchMapping("/{id}/inComplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.inComplete(id);
        return ResponseEntity.ok(todoDto);
    }


}
