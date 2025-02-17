package az.portfoliomanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PortfolioCreateRequest {

    String userEmail;
    String title;
    String description;
    List<SkillRequest> skills;
    List<EducationRequest> educations;
    List<ExperienceRequest> experiences;
    List<ProjectRequest> projects;
}
