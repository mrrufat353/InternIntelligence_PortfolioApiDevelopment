package az.portfoliomanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "portfolios")
@Builder
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long portfolio_id;

    @Column(unique = true, nullable = false)
    String title;
    String description;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    User user;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Skill> skills;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Project> projects;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Education> educations;

    @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<Experience> experiences;

}
