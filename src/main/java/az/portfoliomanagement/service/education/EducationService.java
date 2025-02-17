package az.portfoliomanagement.service.education;

import az.portfoliomanagement.dto.request.EducationRequest;
import az.portfoliomanagement.dto.response.EducationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EducationService {

    EducationResponse addEdu(EducationRequest educationRequest);

    List<EducationResponse> getAll();

    EducationResponse update(String portfolioTitle, Long educationId, EducationRequest educationRequest);

    void delete(Long educationId);
}
