package home.test.Utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class CurrentUserInfo {

    public static String getName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String getUserRole(){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for (GrantedAuthority authority : authorities) {
            return authority.toString();
        }
       return "";
    }

    public static boolean isCurrentUser(String userName){
        return SecurityContextHolder.getContext().getAuthentication().getName().equals(userName) ? true : false;
    }
}
