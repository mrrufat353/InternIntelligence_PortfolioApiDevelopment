package az.portfoliomanagement.dto.response;

import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    Long user_id;
    String username;
    String password;
    String email;
    Role role;
    List<Portfolio> portfolios;

}
