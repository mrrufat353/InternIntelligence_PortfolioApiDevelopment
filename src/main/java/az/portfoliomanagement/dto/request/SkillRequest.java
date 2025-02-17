package az.portfoliomanagement.dto.request;

import az.portfoliomanagement.entity.enums.SkillLevels;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkillRequest {

    String name;
    SkillLevels level;
    String portfolioTitle;
}
