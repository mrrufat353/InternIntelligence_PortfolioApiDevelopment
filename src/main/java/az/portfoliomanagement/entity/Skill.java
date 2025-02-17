package az.portfoliomanagement.entity;

import az.portfoliomanagement.entity.enums.SkillLevels;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "skills")
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long skill_id;

    String name;

    @Enumerated(EnumType.STRING)
    SkillLevels level;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Portfolio portfolio;
}
