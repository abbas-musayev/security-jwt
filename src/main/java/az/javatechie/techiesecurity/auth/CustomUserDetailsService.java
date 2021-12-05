package az.javatechie.techiesecurity.auth;

import az.javatechie.techiesecurity.entity.UserMy;
import az.javatechie.techiesecurity.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("--------loadUserByUsername---------");
        UserMy userMy = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));

        return new User(userMy.getUsername(), userMy.getPassword(),getAuthority(userMy));
    }

    private List<SimpleGrantedAuthority> getAuthority(UserMy user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            log.info("--------GETAUTHORITY-> ROLE:"+role.getName()+"---------");
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public List findAll() {
        List list = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
