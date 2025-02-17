package az.portfoliomanagement.service.project;

import az.portfoliomanagement.dto.request.ProjectRequest;
import az.portfoliomanagement.dto.response.ProjectResponse;
import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.entity.Project;
import az.portfoliomanagement.exception.CustomException;
import az.portfoliomanagement.repo.PortfolioRepo;
import az.portfoliomanagement.repo.ProjectRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepo projectRepo;
    private final ModelMapper modelMapper;
    private final PortfolioRepo portfolioRepo;

    @Override
    public ProjectResponse addProject(ProjectRequest projectRequest) {
        Portfolio portfolio = portfolioRepo.findByTitle(projectRequest.getPortfolioTitle())
                .orElseThrow(()-> new CustomException("Portfolio not found with title: " + projectRequest.getPortfolioTitle()));

        Project project = modelMapper.map(projectRequest, Project.class);
        project.setPortfolio(portfolio);

        Project savedProject = projectRepo.save(project);

        return modelMapper.map(savedProject, ProjectResponse.class);
    }

    @Override
    public ProjectResponse update(String title, Long projectId, ProjectRequest projectRequest) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new CustomException("Project not found with ID: " + projectId));

        if(!project.getPortfolio().getTitle().equals(title)){
            throw new CustomException("Project does not belong to this Portfolio");
        }

        project.setName(projectRequest.getName());
        project.setDescription(project.getDescription());
        project.setProjectUrl(project.getProjectUrl());
        project.setStartDate(projectRequest.getStartDate());
        project.setEndDate(projectRequest.getEndDate());

        Project updatedProject = projectRepo.save(project);
        return new ProjectResponse(
                updatedProject.getProject_id(),
                updatedProject.getName(),
                updatedProject.getDescription(),
                updatedProject.getProjectUrl(),
                updatedProject.getStartDate(),
                updatedProject.getEndDate());
    }

    @Override
    public List<ProjectResponse> getAll() {
        return projectRepo
                .findAll()
                .stream()
                .map(project -> modelMapper.map(project, ProjectResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long projectId) {
        Project project = projectRepo.findById(projectId).orElseThrow(() -> new CustomException("Project not found"));
        projectRepo.delete(project);
    }
}
