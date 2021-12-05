package az.javatechie.techiesecurity.repositories;

import az.javatechie.techiesecurity.entity.UserMy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<UserMy,Integer> {
    Optional<UserMy> findByUsername(String username);
}
