package com.iu.backend.service;

import com.iu.backend.dto.ProjectDto;
import com.iu.backend.model.Project;
import com.iu.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import org.springframework.security.core.context.SecurityContextHolder;
import com.iu.backend.model.User;
import com.iu.backend.repository.UserRepository;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    private ProjectDto convertToDto(Project project) {
        ProjectDto projectdto = new ProjectDto();

        projectdto.setId(project.getId());
        projectdto.setTitle(project.getTitle());
        projectdto.setDescription(project.getDescription());
        projectdto.setArchived(false);

            if(project.getAssignedUsers() != null){
                List<Long> ids = project.getAssignedUsers().stream().map(User::getId).toList();
            } else {
                new ArrayList<>();
            }
            return projectdto;
    
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

    public List<ProjectDto> getProjectsForCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Benutzer nicht gefunden"));
        //Liste in Methode nochmal definiert
        List<Project> projects;

        if("ROLE_PROJECT_LEADER".equals(currentUser.getRole().name())){
            projects = projectRepository.findAll();
        } else {
            projects = projectRepository.findByAssignedUsers(currentUser);
        }
        return projects.stream().map(this::convertToDto).toList();
    }
}
