package az.portfoliomanagement.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PortfolioRequest {

    String title;
    String description;
}
