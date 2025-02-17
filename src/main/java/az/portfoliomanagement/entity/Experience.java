package az.portfoliomanagement.entity;

import az.portfoliomanagement.entity.enums.EmploymentType;
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
@Table(name = "experiences")
@Builder
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long experience_id;

    String title;
    String description;
    String companyName;

    @Enumerated(EnumType.STRING)
    EmploymentType employmentType;

    LocalDate startDate;
    LocalDate endDate;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Portfolio portfolio;
}
