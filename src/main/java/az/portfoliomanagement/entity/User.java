package az.portfoliomanagement.entity;

import az.portfoliomanagement.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long user_id;

    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String email;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Portfolio> portfolios;

}
