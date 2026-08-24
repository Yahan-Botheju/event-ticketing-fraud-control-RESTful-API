package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.web.DTOs;

public record AuthResponseDTO(
        String accessToken,
        String refreshToken,
        String tokenType,
        Long userId,
        String email,
        String role
) {
    public AuthResponseDTO(String accessToken, String refreshToken, Long userId, String email, String role){
        this(accessToken, refreshToken, "Bearer", userId, email, role);
    }
}
