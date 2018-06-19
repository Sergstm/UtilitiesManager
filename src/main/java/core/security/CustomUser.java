package core.security;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public CustomUser() {
    }

    public CustomUser(String login, String password, UserRole role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public CustomUser(CustomUser customUser) {
        this.login = customUser.getLogin();
        this.password = customUser.getPassword();
        this.role = customUser.getRole();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
