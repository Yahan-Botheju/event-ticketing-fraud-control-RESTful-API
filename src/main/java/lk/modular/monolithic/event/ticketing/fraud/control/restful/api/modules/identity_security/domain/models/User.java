package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long userId;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime createdAt;
}
