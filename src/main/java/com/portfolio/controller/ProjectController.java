package com.portfolio.controller;

import com.portfolio.model.Project;
import com.portfolio.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "*") // allow frontend access
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    // 🔹 GET all projects
    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // 🔹 GET single project by ID
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id) {
        return projectRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 POST new project
    @PostMapping
    public ResponseEntity<Project> addProject(@Valid @RequestBody Project project) {
        Project saved = projectRepository.save(project);
        return ResponseEntity.ok(saved);
    }

    // 🔹 PUT update project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @Valid @RequestBody Project projectDetails) {
        return projectRepository.findById(id).map(project -> {
            project.setTitle(projectDetails.getTitle());
            project.setDescription(projectDetails.getDescription());
            project.setUrl(projectDetails.getUrl());
            Project updated = projectRepository.save(project);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

    // 🔹 DELETE project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        if (!projectRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        projectRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
