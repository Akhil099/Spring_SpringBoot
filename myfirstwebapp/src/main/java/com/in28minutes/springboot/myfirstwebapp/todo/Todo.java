package com.in28minutes.springboot.myfirstwebapp.todo;

//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.Id;
//import jakarta.validation.constraints.NotEmpty;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//
//@Entity
//public class Todo {
//
//    //These things should be saved in a database
//    //Firstly we will use a static list of Todos then we will use Database(H2, MySQL)
//
//    @Id
//    @GeneratedValue
//    private int id;
//    private String username;
//
//    @NotEmpty(message = "Description should not be empty")
//    private String description;
//
//    private LocalDate targetDate;
//    private boolean done;
//
//    public Todo(){
//
//    }
//
//    public Todo(int id, String username, String description, LocalDate targetDate, boolean done) {
//        this.id = id;
//        this.username = username;
//        this.description = description;
//        this.targetDate = targetDate;
//        this.done = done;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public LocalDate getTargetDate() {
//        return targetDate;
//    }
//
//    public void setTargetDate(LocalDate targetDate) {
//        this.targetDate = targetDate;
//    }
//
//    public boolean isDone() {
//        return done;
//    }
//
//    public void setDone(boolean done) {
//        this.done = done;
//    }
//
//    @Override
//    public String toString() {
//        return "Todo{" +
//                "id=" + id +
//                ", username='" + username + '\'' +
//                ", description='" + description + '\'' +
//                ", targetDate=" + targetDate +
//                ", done=" + done +
//                '}';
//    }
//}


import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;


@Entity
public class Todo {

    public Todo() {

    }

    public Todo(Integer id, String username, String description, LocalDate targetDate, boolean done) {
        super();
        this.id = id;
        this.username = username;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    private String description;
    private LocalDate targetDate;
    private boolean done;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", targetDate="
                + targetDate + ", done=" + done + "]";
    }

}