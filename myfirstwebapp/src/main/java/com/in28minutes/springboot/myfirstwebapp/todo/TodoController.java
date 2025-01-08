package com.in28minutes.springboot.myfirstwebapp.todo;

//import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//@Controller
//Making it as a backup so we would comment out @Controller, to make sure that there are no 2 controllers
@SessionAttributes("name")
public class TodoController {

    private TodoService todoservice;

    public TodoController(TodoService todoservice) {
        super();
        this.todoservice = todoservice;
    }


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        String username = (String)model.get("name");
        List<Todo> todos = todoservice.findByUserName(getLoggedinUsername());
//        Logger logger = LoggerFactory.getLogger(getClass());
//        logger.info("The name was  {}",model.get("name"));
        model.put("todos", todos);
        return "listTodos";
    }

    //It is requesting both GET and POST method
    @RequestMapping(value="add-todo", method = RequestMethod.GET)
    public String showNewTodo(ModelMap model){
        String username = (String) model.get("name");
        Todo todo = new Todo(0, username, "", LocalDate.now(), false);
        model.put("todo", todo);
        return "todo";
    }
//    @RequestMapping(value="add-todo", method = RequestMethod.POST)
//    public String addNewTodo(@ModelAttribute("todo")Todo newTodo, ModelMap model){
//        List<Todo> todos = todoservice.getAllTodos();
//        todos.add(newTodo);
//        model.addAttribute("todos",todos);
//        return "redirect:list-todos";
//    }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model,
//                             @Valid
                             @ModelAttribute("todo") Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }
        String username = (String) model.get("name");
        todoservice.addNewTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todos")
    public String deleteTodo(@RequestParam int id){
        todoservice.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model){
        Todo todo = todoservice.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model,
//                             @Valid
                             Todo todo, BindingResult result){
        if(result.hasErrors()){
            return "todo";
        }

        String username = (String)model.get("name");
        todo.setUsername(username);
        todoservice.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
