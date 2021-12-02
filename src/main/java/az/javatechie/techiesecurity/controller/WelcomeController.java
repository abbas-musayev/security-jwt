package az.javatechie.techiesecurity.controller;

import az.javatechie.techiesecurity.entity.AuthRequest;
import az.javatechie.techiesecurity.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WelcomeController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    public WelcomeController(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    public String getWelcome(){
        log.info("Welcome Controller");
        return "Welcome";
    }

    @PostMapping("/authenticate")
    public String getToken(@RequestBody AuthRequest authRequest) throws IllegalAccessException {
        try{
            log.info("authenticate CONTROLLERE DAXIL OLDU");
            System.out.println("authenticate CONTROLLERE DAXIL OLDU");
               Authentication authentication= new UsernamePasswordAuthenticationToken(
                        authRequest.getPassword(),
                        authRequest.getUsername()
               );
               authenticationManager.authenticate(authentication);


        }catch (Exception e){
            throw new IllegalAccessException(e.getMessage());
        }
        log.info("TOKEN QAYTARILDI");
        return jwtUtil.generateToken(authRequest.getUsername());
    }
}
