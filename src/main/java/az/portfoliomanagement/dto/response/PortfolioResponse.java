package az.portfoliomanagement.dto.response;

import az.portfoliomanagement.entity.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PortfolioResponse {

    Long portfolio_id;
    String title;
    String description;
    User user;
    List<Skill> skills;
    List<Project> projects;
    List<Education> educations;
    List<Experience> experiences;

    public PortfolioResponse(Long portfolio_id, String title, String description) {
        this.portfolio_id = portfolio_id;
        this.title = title;
        this.description = description;
    }
}
