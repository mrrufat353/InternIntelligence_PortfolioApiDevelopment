package az.portfoliomanagement.dto.response;

import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.entity.enums.EmploymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExperienceResponse {

    Long experience_id;
    String title;
    String description;
    String companyName;
    EmploymentType employmentType;
    LocalDate startDate;
    LocalDate endDate;
    Portfolio portfolio;

    public ExperienceResponse(Long experienceId, String title, String description, String companyName,
                              EmploymentType employmentType, LocalDate startDate, LocalDate endDate) {
        this.experience_id = experienceId;
        this.title = title;
        this.description = description;
        this.companyName = companyName;
        this.employmentType = employmentType;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
