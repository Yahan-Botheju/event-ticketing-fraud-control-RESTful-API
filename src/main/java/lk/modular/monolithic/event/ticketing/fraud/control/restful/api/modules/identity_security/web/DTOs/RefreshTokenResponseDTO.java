package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenResponseDTO {
    private String refreshToken;
    private String accessToken;
    private Long userId;
    private String email;
    private String role;
}
