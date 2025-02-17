package az.portfoliomanagement.dto.response;

import az.portfoliomanagement.entity.enums.EducationDegree;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EducationResponse {

    Long education_id;
    String schoolName;
    String fieldOfStudy;
    EducationDegree educationDegree;
    LocalDate startDate;
    LocalDate endDate;
    Long portfolio_id;

    public EducationResponse(Long educationId, String schoolName, String fieldOfStudy, EducationDegree educationDegree,
                             LocalDate startDate, LocalDate endDate) {
        this.education_id = educationId;
        this.schoolName = schoolName;
        this.fieldOfStudy = fieldOfStudy;
        this.educationDegree = educationDegree;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
