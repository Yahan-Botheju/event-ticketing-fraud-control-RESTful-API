package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequestDTO {

    @NotBlank(message = "Refresh token cannot be empty")
    private String refreshToken;
}
