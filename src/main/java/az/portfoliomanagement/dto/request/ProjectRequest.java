package az.portfoliomanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectRequest {

    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    String projectUrl;
    String portfolioTitle;
}
