package az.portfoliomanagement.dto.response;

import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.entity.enums.SkillLevels;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SkillResponse {

    Long skill_id;
    String name;
    SkillLevels level;
    Portfolio portfolio;

    public SkillResponse(Long skill_id, SkillLevels level, String name) {
        this.skill_id = skill_id;
        this.level = level;
        this.name = name;
    }
}
