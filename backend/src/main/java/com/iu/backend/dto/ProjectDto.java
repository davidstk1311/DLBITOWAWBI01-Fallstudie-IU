package com.iu.backend.dto;
import lombok.Getter;
import lombok.Setter;
//Lombok wird ausprobiert für getter und setter 

public class ProjectDto {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private boolean isArchived;

    public ProjectDto() {
    }
    
    public ProjectDto(Long id, String title, String description, boolean isArchived) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isArchived = isArchived;
    }

}
