package com.iu.backend.model;

import jakarta.persistence.*;
import java.util.*;
import java.lang.annotation.Inherited;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private taskStatus status = taskStatus.OPEN;
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User chosenUser;

    public Task() {
    }

    public Task(long id, String title, String description, Enum status, Project project, User chosenUser) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.project = project;
        this.chosenUser = chosenUser;
    }

//Getter und Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enum getStatus() {
        return status;
    }

    public void setStatus(Enum status) {
        this.status = status;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getChosenUser() {
        return chosenUser;
    }

    public void setChosenUser(User chosenUser) {
        this.chosenUser = chosenUser;
    }


}
