package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models;

import lombok.*;

import java.time.LocalDateTime;

@Getter
public class User {
    private Long userId;
    private String fullName;
    private String email;
    private String password;
    private Role role;
    private LocalDateTime createdAt;

    public User(Long userId,String fullName, String email, String password, Role role, LocalDateTime createdAt) {
       this.userId = userId;
       this.fullName = fullName;
       this.email = email;
       this.password = password;
       this.role = role;
       this.createdAt = createdAt;
    }
}
