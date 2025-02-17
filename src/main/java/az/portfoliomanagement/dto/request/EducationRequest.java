package az.portfoliomanagement.dto.request;

import az.portfoliomanagement.entity.enums.EducationDegree;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducationRequest {

    String schoolName;
    String fieldOfStudy;
    EducationDegree educationDegree;
    LocalDate startDate;
    LocalDate endDate;
    String portfolioTitle;
}
