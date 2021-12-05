package az.javatechie.techiesecurity;

import az.javatechie.techiesecurity.entity.Role;
import az.javatechie.techiesecurity.entity.UserMy;
import az.javatechie.techiesecurity.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
public class TechieSecurityApplication {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public static void main(String[] args) {
        SpringApplication.run(TechieSecurityApplication.class, args);
    }

    @PostConstruct
    public void initUsers() {
        log.info("------------InitUsers----------");

        String password = encoder.encode("123456");
        List<UserMy> userMYStream = Stream.of(
                new UserMy(1L,
                        "Abbas",
                        password,
                        "abbas@gmail.com",
                        Arrays.asList(
                        		new Role(null, "ADMIN"),
								new Role(null,"USER"),
                                new Role(null,"ABBAS"))),
				new UserMy(2L,
						"Resul",
						password,
						"resul@gmail.com",
						Arrays.asList(
								new Role(null, "USER"))),
                new UserMy(3L,
                        "Novruz",
                        password,
                        "novruz@gmail.com",
                        Arrays.asList(
                                new Role(null, "NOVRUZ")))
		).collect(Collectors.toList());

        userRepo.saveAll(userMYStream);
    }

}
