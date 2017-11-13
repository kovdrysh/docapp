package home.test.services;


import home.test.dao.UserDao;
import home.test.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private static final Logger logger = Logger.getLogger(CustomUserDetailsService.class);

    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        User user = userDao.get("nickname", nickname);
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(user.getUserRole()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getNickname(), user.getPassword(), roles);
        return userDetails;
    }
}
