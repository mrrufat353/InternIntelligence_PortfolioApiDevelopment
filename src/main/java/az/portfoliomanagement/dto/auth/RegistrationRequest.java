package az.portfoliomanagement.dto.auth;

import az.portfoliomanagement.entity.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequest {

    @NotBlank(message = "First name cannot be blank")
    @Size(max = 50, message = "First name cannot exceed 50 characters")
    String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Column(unique = true, nullable = false)
    String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role cannot be null")
    Role role;
}
