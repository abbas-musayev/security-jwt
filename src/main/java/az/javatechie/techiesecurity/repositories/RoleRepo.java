package az.javatechie.techiesecurity.repositories;

import az.javatechie.techiesecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
