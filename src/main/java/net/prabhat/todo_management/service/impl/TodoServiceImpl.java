package net.prabhat.todo_management.service.impl;

import lombok.AllArgsConstructor;
import net.prabhat.todo_management.dto.TodoDto;
import net.prabhat.todo_management.entity.Todo;
import net.prabhat.todo_management.exception.ResourceNotFound;
import net.prabhat.todo_management.repository.TodoRepository;
import net.prabhat.todo_management.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo  todo=  modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo,TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {

        Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFound("there Is no todo with ID: "+id));

        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodo() {
        List<Todo> todos =todoRepository.findAll();

        return todos.stream().map((todo)->modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto dto, Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFound("there Is no todo with ID: "+id));

        todo.setTitle(dto.getTitle());
        todo.setDescription(dto.getDescription());
        todo.setCompleted(dto.isCompleted());

        Todo savedTodo = todoRepository.save(todo);

        return modelMapper.map(todo, TodoDto.class);

    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).
                orElseThrow(()->new ResourceNotFound("there Is no todo with ID: "+id));

        todoRepository.delete(todo);

    }

}
