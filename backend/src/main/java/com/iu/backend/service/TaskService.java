package com.iu.backend.service;

import com.iu.backend.dto.TaskDto;
import com.iu.backend.model.Project;
import com.iu.backend.model.TaskStatus;
import com.iu.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import com.iu.backend.model.Task;
import com.iu.backend.repository.ProjectRepository;

import java.util.*;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    private TaskDto convertToDto(Task task) {
        TaskDto taskDto = new TaskDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getStatus() == TaskStatus.COMPLETED);
        taskDto.setProjectId(task.getProject().getId());
        return taskDto;
    }

    //Tasks abrufen
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .toList();
    }

    public TaskDto createTask(TaskDto taskDto) {
        Project project = projectRepository.findById(taskDto.getProjectId()).orElseThrow(() -> new NoSuchElementException("Projekt nicht gefunden. ID: " + taskDto.getProjectId()));
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setProject(project);

        Task saved = taskRepository.save(task);
        return convertToDto(saved);
    }
}
