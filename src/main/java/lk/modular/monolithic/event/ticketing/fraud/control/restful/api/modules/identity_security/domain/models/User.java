package lk.modular.monolithic.event.ticketing.fraud.control.restful.api.modules.identity_security.domain.models;


import java.time.LocalDateTime;


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

    /* __SETTERS__ */

    public Long getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public LocalDateTime getCreatedAt() { return createdAt; }



    /* __FACTORY_METHOD__ */

    //create factor method for new user registration
    public static User registerNewUser(String fullName, String email, String password, Role role) {
        Role finalRole = (role != null) ? role : Role.ATTENDEE;
        return new User(null, fullName, email, password, finalRole, LocalDateTime.now());
    }
}
