package az.portfoliomanagement.dto.response;

import az.portfoliomanagement.entity.Portfolio;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectResponse {

    Long project_id;
    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    String projectUrl;
    Portfolio portfolio;

    public ProjectResponse(Long projectId, String name, String description, String projectUrl,
                           LocalDate startDate, LocalDate endDate) {
        this.project_id = projectId;
        this.name = name;
        this.description = description;
        this.projectUrl = projectUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
