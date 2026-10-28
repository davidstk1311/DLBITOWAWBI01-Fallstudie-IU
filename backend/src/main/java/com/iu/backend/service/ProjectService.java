package com.iu.backend.service;

import com.iu.backend.dto.ProjectDto;
import com.iu.backend.model.Project;
import com.iu.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    private ProjectDto convertToDto(Project project) {
        return new ProjectDto(project.getId(), project.getTitle(), project.getDescription(), project.isArchived());

    }
    //Projekte abrufen
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(this::convertToDto)
            .toList();
    }

     //Projekt erstellen

    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        
        Project saved = projectRepository.save(project);
        return convertToDto(saved);
    }


    public ProjectDto getProjectbyId(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Projekt nicht gefunden mit ID: " + id));
        return convertToDto(project);
    }
    public void removeProject(long id) {
        projectRepository.deleteById(id);
    }
}
