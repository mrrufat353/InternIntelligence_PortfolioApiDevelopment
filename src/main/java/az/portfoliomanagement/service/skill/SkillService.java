package az.portfoliomanagement.service.skill;

import az.portfoliomanagement.dto.request.SkillRequest;
import az.portfoliomanagement.dto.response.SkillResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillService {

    List<SkillResponse> getAll();

    SkillResponse addSkill(SkillRequest skillRequest);

    SkillResponse update(Long skillId, SkillRequest skillRequest, String portfolioTitle);

    void delete(Long skillId);
}
