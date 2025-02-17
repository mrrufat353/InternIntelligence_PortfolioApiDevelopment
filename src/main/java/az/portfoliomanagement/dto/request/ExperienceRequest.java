package az.portfoliomanagement.dto.request;

import az.portfoliomanagement.entity.enums.EmploymentType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExperienceRequest {

    String title;
    String description;
    String companyName;
    EmploymentType employmentType;
    LocalDate startDate;
    LocalDate endDate;
    String portfolioTitle;
}
