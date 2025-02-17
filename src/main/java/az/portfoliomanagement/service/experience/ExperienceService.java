package az.portfoliomanagement.service.experience;

import az.portfoliomanagement.dto.request.ExperienceRequest;
import az.portfoliomanagement.dto.response.ExperienceResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExperienceService {

    ExperienceResponse addExperience(ExperienceRequest experienceRequest);

    List<ExperienceResponse> getAll();

    ExperienceResponse update(String title, Long experienceId, ExperienceRequest experienceRequest);

    void delete(Long experienceId);
}
