package az.portfoliomanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "projects")
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long project_id;

    String name;
    String description;
    LocalDate startDate;
    LocalDate endDate;
    String projectUrl;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Portfolio portfolio;
}
