package az.portfoliomanagement.service.portfolio;

import az.portfoliomanagement.dto.request.*;
import az.portfoliomanagement.dto.response.PortfolioResponse;
import az.portfoliomanagement.entity.*;
import az.portfoliomanagement.exception.CustomException;
import az.portfoliomanagement.repo.PortfolioRepo;
import az.portfoliomanagement.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioRepo portfolioRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public PortfolioResponse findByTitle(String title) {
        Portfolio portfolio = portfolioRepo.findByTitle(title)
                .orElseThrow(() -> new CustomException("Portfolio with title '" + title + "' not found"));

        return modelMapper.map(portfolio, PortfolioResponse.class);
    }

    public PortfolioResponse create(PortfolioCreateRequest portfolioCreateRequestDto) {
        if (portfolioRepo.existsByTitle(portfolioCreateRequestDto.getTitle())) {
            throw new CustomException("Portfolio with title '" + portfolioCreateRequestDto.getTitle() + "' already exists.");
        }

        User user = userRepo.findByEmail(portfolioCreateRequestDto.getUserEmail())
                .orElseThrow(() -> new CustomException("User not found with email: " + portfolioCreateRequestDto.getUserEmail()));
        Portfolio portfolio = Portfolio.builder()
                .title(portfolioCreateRequestDto.getTitle())
                .description(portfolioCreateRequestDto.getDescription())
                .user(user)
                .build();

        portfolio.setSkills(mapSkills(portfolioCreateRequestDto.getSkills(), portfolio));
        portfolio.setEducations(mapEducations(portfolioCreateRequestDto.getEducations(), portfolio));
        portfolio.setExperiences(mapExperiences(portfolioCreateRequestDto.getExperiences(), portfolio));
        portfolio.setProjects(mapProjects(portfolioCreateRequestDto.getProjects(), portfolio));

        Portfolio persistedPortfolio = portfolioRepo.save(portfolio);

        return modelMapper.map(persistedPortfolio, PortfolioResponse.class);
    }

    @Override
    public PortfolioResponse updatePortfolio(String title, PortfolioRequest portfolioRequest) {
        Portfolio portfolio = portfolioRepo.findByTitle(title)
                .orElseThrow(() -> new CustomException("Portfolio not found with title: " + title));

        if (!portfolio.getTitle().equals(portfolioRequest.getTitle()) &&
            portfolioRepo.existsByTitle(portfolioRequest.getTitle())) {
            throw new CustomException("Portfolio with title '" + portfolioRequest.getTitle() + "' already exists.");
        }

        portfolio.setTitle(portfolioRequest.getTitle());
        portfolio.setDescription(portfolioRequest.getDescription());

        Portfolio updatedPortfolio = portfolioRepo.save(portfolio);
        return modelMapper.map(updatedPortfolio, PortfolioResponse.class);
    }

    @Override
    public void delete(String title) {
        Portfolio portfolio = portfolioRepo.findByTitle(title)
                .orElseThrow(() -> new CustomException("Portfolio not found with title: " + title));
        portfolioRepo.delete(portfolio);
    }

    @Override
    public List<PortfolioResponse> findAll() {
        return portfolioRepo
                .findAll()
                .stream()
                .map(portfolio -> modelMapper.map(portfolio, PortfolioResponse.class))
                .collect(Collectors.toList());
    }

    private List<Skill> mapSkills(List<SkillRequest> skillRequests, Portfolio portfolio) {
        return skillRequests.stream()
                .map(skillRequest -> Skill.builder()
                        .name(skillRequest.getName())
                        .level(skillRequest.getLevel())
                        .portfolio(portfolio)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Education> mapEducations(List<EducationRequest> educationRequests, Portfolio portfolio) {
        return educationRequests.stream()
                .map(educationRequest -> Education.builder()
                        .schoolName(educationRequest.getSchoolName())
                        .fieldOfStudy(educationRequest.getFieldOfStudy())
                        .educationDegree(educationRequest.getEducationDegree())
                        .startDate(educationRequest.getStartDate())
                        .endDate(educationRequest.getEndDate())
                        .portfolio(portfolio)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Experience> mapExperiences(List<ExperienceRequest> experienceRequests, Portfolio portfolio) {
        return experienceRequests.stream()
                .map(experienceRequest -> Experience.builder()
                        .title(experienceRequest.getTitle())
                        .description(experienceRequest.getDescription())
                        .companyName(experienceRequest.getCompanyName())
                        .employmentType(experienceRequest.getEmploymentType())
                        .startDate(experienceRequest.getStartDate())
                        .endDate(experienceRequest.getEndDate())
                        .portfolio(portfolio)
                        .build())
                .collect(Collectors.toList());
    }

    private List<Project> mapProjects(List<ProjectRequest> projectRequests, Portfolio portfolio) {
        return projectRequests.stream()
                .map(projectRequest -> Project.builder()
                        .name(projectRequest.getName())
                        .description(projectRequest.getDescription())
                        .startDate(projectRequest.getStartDate())
                        .endDate(projectRequest.getEndDate())
                        .projectUrl(projectRequest.getProjectUrl())
                        .portfolio(portfolio)
                        .build())
                .collect(Collectors.toList());
    }

}
