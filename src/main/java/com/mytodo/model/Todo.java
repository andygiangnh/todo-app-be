package com.mytodo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="todo")
@Getter
@Setter
public class Todo {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String description;
    private boolean completed;
}
