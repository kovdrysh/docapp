package home.test.model;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Document(collection = User.COLLECTION_NAME)
public class User implements UserDetails {
    public static final String COLLECTION_NAME = "users";

    private String name;
    private String surname;
    private String email;
    private String nickname;
    private String password;
    private int userRole;

    public User(String name, String surname, String email, String nickname, String password, int userRole) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.userRole = userRole;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return name;
    }

    public boolean isAccountNonExpired() {
        return false;
    }

    public boolean isAccountNonLocked() {
        return false;
    }

    public boolean isCredentialsNonExpired() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
}
