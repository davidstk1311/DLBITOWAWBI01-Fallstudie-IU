package com.iu.backend.dto;
import lombok.Getter;
import lombok.Setter;
import com.iu.backend.model.TaskStatus;

public class TaskDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private Long projectId;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private TaskStatus status;
    @Getter
    @Setter
    private boolean isCompleted;

    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, TaskStatus status, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.isCompleted = isCompleted;
    }
}
