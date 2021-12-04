package az.javatechie.techiesecurity;

import az.javatechie.techiesecurity.entity.UserMY;
import az.javatechie.techiesecurity.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class TechieSecurityApplication {

	@Autowired
	private UserRepo userRepo;

	@PostConstruct
	public void initUsers(){
		List<UserMY> userMYStream = Stream.of(
				new UserMY(1, "Abbas", "qwe123", "abbas@gmail.com"),
				new UserMY(2, "Resul", "qwe123", "resul@gmail.com"),
				new UserMY(3, "Kamran", "qwe123", "kamran@gmail.com")
		).collect(Collectors.toList());

		userRepo.saveAll(userMYStream);
	}

	public static void main(String[] args) {
		SpringApplication.run(TechieSecurityApplication.class, args);
	}

}
