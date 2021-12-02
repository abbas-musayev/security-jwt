package az.javatechie.techiesecurity.repositories;

import az.javatechie.techiesecurity.entity.UserMY;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<UserMY,Integer> {
    Optional<UserMY> findByUsername(String username);
}
