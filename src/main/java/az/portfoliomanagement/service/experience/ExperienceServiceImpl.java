package az.portfoliomanagement.service.experience;

import az.portfoliomanagement.dto.request.ExperienceRequest;
import az.portfoliomanagement.dto.response.ExperienceResponse;
import az.portfoliomanagement.entity.Experience;
import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.exception.CustomException;
import az.portfoliomanagement.repo.ExperienceRepo;
import az.portfoliomanagement.repo.PortfolioRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepo experienceRepo;
    private final PortfolioRepo portfolioRepo;
    private final ModelMapper modelMapper;

    public ExperienceResponse addExperience(ExperienceRequest experienceRequest) {
        Portfolio portfolio = portfolioRepo.findByTitle(experienceRequest.getPortfolioTitle()).
                orElseThrow(() -> new CustomException("Portfolio not found with ID: " + experienceRequest.getPortfolioTitle()));

        Experience experience = modelMapper.map(experienceRequest, Experience.class);
        experience.setPortfolio(portfolio);

        Experience savedExperience = experienceRepo.save(experience);

        return modelMapper.map(savedExperience, ExperienceResponse.class);
    }

    @Override
    public List<ExperienceResponse> getAll() {
        return experienceRepo
                .findAll()
                .stream()
                .map(experience -> modelMapper.map(experience, ExperienceResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExperienceResponse update(String title, Long experienceId, ExperienceRequest experienceRequest) {
        Experience experience = experienceRepo.findById(experienceId)
                .orElseThrow(() -> new CustomException("Experience not found with ID: " + experienceId));

        if (!experience.getPortfolio().getTitle().equals(title)) {
            throw new CustomException("Experience does not belong to this portfolio");
        }

        experience.setTitle(experienceRequest.getTitle());
        experience.setCompanyName(experienceRequest.getCompanyName());
        experience.setDescription(experienceRequest.getDescription());
        experience.setEmploymentType(experienceRequest.getEmploymentType());
        experience.setStartDate(experienceRequest.getStartDate());
        experience.setEndDate(experienceRequest.getEndDate());

        Experience updatedExperience = experienceRepo.save(experience);
        return new ExperienceResponse(
                updatedExperience.getExperience_id(),
                updatedExperience.getTitle(),
                updatedExperience.getDescription(),
                updatedExperience.getCompanyName(),
                updatedExperience.getEmploymentType(),
                updatedExperience.getStartDate(),
                updatedExperience.getEndDate());
    }

    @Override
    public void delete(Long experienceId) {
        Experience experience = experienceRepo.findById(experienceId)
                .orElseThrow(() -> new CustomException("Experience not found with ID: " + experienceId));
        experienceRepo.delete(experience);
    }
}
