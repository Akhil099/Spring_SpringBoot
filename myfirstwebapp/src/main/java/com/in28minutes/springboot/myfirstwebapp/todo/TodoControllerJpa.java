package com.in28minutes.springboot.myfirstwebapp.todo;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

    private TodoService todoservice;
    private TodoRepository todoRepository;

    public TodoControllerJpa(TodoService todoservice, TodoRepository todoRepository) {
        super();
        this.todoservice = todoservice;
        this.todoRepository = todoRepository;
    }


//    public TodoControllerJpa(TodoService todoservice) {
//        super();
//        this.todoservice = todoservice;
//    }


    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model){
        String username = (String)model.get("name");
        List<Todo> todos = todoRepository.findByUsername(getLoggedinUsername());
//        Logger logger = LoggerFactory.getLogger(getClass());
//        logger.info("The name was  {}",model.get("name"));
        model.put("todos", todos);
//        todoRepository.getById(10001);
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
        todo.setUsername(username);
        todoRepository.save(todo);
//        todoservice.addNewTodo(username, todo.getDescription(), todo.getTargetDate(), false);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "delete-todos")
    public String deleteTodo(@RequestParam int id){
        todoRepository.deleteById(id);
//        todoservice.deleteById(id);
        return "redirect:list-todos";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model){
        Todo todo = todoRepository.findById(id).get();
        //Todo todo = todoservice.findById(id);
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
        todoRepository.save(todo);
        //todoservice.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedinUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}



