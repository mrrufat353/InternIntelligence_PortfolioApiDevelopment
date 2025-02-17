package az.portfoliomanagement.dto.request;

import az.portfoliomanagement.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequest {

    String username;
    String password;
    String email;
    Role role;

}
