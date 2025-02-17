package az.portfoliomanagement.controller;

import az.portfoliomanagement.dto.request.ProjectRequest;
import az.portfoliomanagement.dto.response.ProjectResponse;
import az.portfoliomanagement.service.project.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add")
    public ResponseEntity<ProjectResponse> addProject(ProjectRequest projectRequest) {
        return new ResponseEntity<>(projectService.addProject(projectRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{project_id}")
    public ResponseEntity<ProjectResponse> update(String title, @PathVariable Long project_id,
                                                  @Valid @RequestBody ProjectRequest projectRequest){
        return new ResponseEntity<>(projectService.update(title, project_id, projectRequest), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectResponse>> findAll(){
        return new ResponseEntity<>(projectService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{project_id}")
    public void delete(@PathVariable Long project_id){
        projectService.delete(project_id);
    }
}
