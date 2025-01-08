package com.in28minutes.springboot.myfirstwebapp.todo;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int  todosCount = 0;

    static{
        todos.add(new Todo(++todosCount, "akhil", "Learn AWS 1", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "akhil", "Full stack Development 1", LocalDate.now().plusYears(1), false));
        todos.add(new Todo(++todosCount, "akhil", "DevOps 1", LocalDate.now().plusYears(1), false));
    }

    public List<Todo> findByUserName(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public List<Todo> getAllTodos(){return todos;}

    public void addNewTodo(String username, String description, LocalDate targetDate, boolean done){
        int count = ++todosCount;
        Todo todo = new Todo(count, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteById(int id) {
//        Todo todoDelete = null;
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);  //This is more efficient way
//        for (Todo todo : todos) {
//            if (todo.getId() == id) {
//                todoDelete = todo;
//                break;
//            }
//        }
//        if (todoDelete != null) {
//            todos.remove(todoDelete);
//        }
    }

    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo-> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteById(todo.getId());
        todos.add(todo);
    }


}
