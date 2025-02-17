package az.portfoliomanagement.entity;

import az.portfoliomanagement.entity.enums.EducationDegree;
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
@Table(name = "educations")
@Builder
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long education_id;

    String schoolName;
    String fieldOfStudy;

    @Enumerated(EnumType.STRING)
    EducationDegree educationDegree;

    LocalDate startDate;
    LocalDate endDate;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    Portfolio portfolio;
}
