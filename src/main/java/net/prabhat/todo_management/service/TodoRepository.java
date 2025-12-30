package net.prabhat.todo_management.service;

import net.prabhat.todo_management.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {

}
