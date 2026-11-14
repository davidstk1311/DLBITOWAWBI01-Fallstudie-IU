package com.iu.backend.dto;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
//Lombok wird ausprobiert für die meisten getter und setter 

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

    private List<Long> assignedUserIDs = new ArrayList<>();
    public ProjectDto() {
    }

    public List<Long> getAssignedUserIDs() {
        return assignedUserIDs;
    }

    public void setAssignedUserIDs(List<Long> assignedUserIDs){
        this.assignedUserIDs = assignedUserIDs;
    }
    
    public ProjectDto(Long id, String title, String description, boolean isArchived) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isArchived = isArchived;
    }

}
