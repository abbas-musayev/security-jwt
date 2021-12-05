package az.javatechie.techiesecurity.controller;

import az.javatechie.techiesecurity.auth.CustomUserDetailsService;
import az.javatechie.techiesecurity.entity.UserMy;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Slf4j
public class UserController {

    private final CustomUserDetailsService userDetailsService;

    public UserController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //    @PreAuthorize("hasRole('ROLE_ABBAS')")
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public String listAdmin(){
        log.info("--------Admin Controller---------");
        return "Welcome Admin";
    }

//    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public String listUsers(){
        log.info("--------Users Controller---------");
        return "Welcome USER";
    }

    @GetMapping("/all")
    public List<UserMy> getAll(){
        return userDetailsService.findAll();
    }
    @GetMapping("/getbyid")
    public List<UserMy> getById(@RequestParam Long id){
        List<UserMy> all = userDetailsService.findAll();
        List<UserMy> collect = all.stream()
                .filter((item) -> item.getId().equals(id))
                .collect(Collectors.toList());
        return collect;
    }

}
