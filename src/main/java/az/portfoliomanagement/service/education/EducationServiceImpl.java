package az.portfoliomanagement.service.education;

import az.portfoliomanagement.dto.request.EducationRequest;
import az.portfoliomanagement.dto.response.EducationResponse;
import az.portfoliomanagement.entity.Education;
import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.exception.CustomException;
import az.portfoliomanagement.repo.EducationRepo;
import az.portfoliomanagement.repo.PortfolioRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationServiceImpl implements EducationService {

    private final EducationRepo educationRepo;
    private final PortfolioRepo portfolioRepo;
    private final ModelMapper modelMapper;

    @Override
    public EducationResponse addEdu(EducationRequest educationRequest) {
        Portfolio portfolio = portfolioRepo.findByTitle(educationRequest.getPortfolioTitle())
                .orElseThrow(() -> new CustomException("Portfolio not found with title: " + educationRequest.getPortfolioTitle()));

        Education education = modelMapper.map(educationRequest, Education.class);
        education.setPortfolio(portfolio);

        Education savedEducation = educationRepo.save(education);

        return modelMapper.map(savedEducation, EducationResponse.class);
    }

    @Override
    public List<EducationResponse> getAll() {
        return educationRepo
                .findAll()
                .stream()
                .map(education -> modelMapper.map(education, EducationResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public EducationResponse update(String portfolioTitle, Long educationId, EducationRequest educationRequest) {
        Education education = educationRepo.findById(educationId)
                .orElseThrow(() -> new CustomException("Education not found with ID: " + educationId));

        if (!education.getPortfolio().getTitle().equals(portfolioTitle)) {
            throw new CustomException("Education does not belong to this portfolio.");
        }

        education.setSchoolName(educationRequest.getSchoolName());
        education.setFieldOfStudy(educationRequest.getFieldOfStudy());
        education.setEducationDegree(educationRequest.getEducationDegree());
        education.setStartDate(educationRequest.getStartDate());
        education.setEndDate(educationRequest.getEndDate());

        Education updatedEducation = educationRepo.save(education);
        return new EducationResponse(
                updatedEducation.getEducation_id(),
                updatedEducation.getSchoolName(),
                updatedEducation.getFieldOfStudy(),
                updatedEducation.getEducationDegree(),
                updatedEducation.getStartDate(),
                updatedEducation.getEndDate());
    }

    @Override
    public void delete(Long educationId) {
        Education education = educationRepo.findById(educationId)
                .orElseThrow(() -> new CustomException("Education not found"));
        educationRepo.delete(education);
    }
}
