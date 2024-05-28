package com.example.springboottodo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "is_done")
    private boolean done;

    @Column(name = "due_date")
    private String dueDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
