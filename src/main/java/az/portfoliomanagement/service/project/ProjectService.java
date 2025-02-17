package az.portfoliomanagement.service.project;

import az.portfoliomanagement.dto.request.ProjectRequest;
import az.portfoliomanagement.dto.response.ProjectResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    ProjectResponse addProject(ProjectRequest projectRequest);

    ProjectResponse update(String title, Long projectId, ProjectRequest projectRequest);

    List<ProjectResponse> getAll();

    void delete(Long projectId);
}
