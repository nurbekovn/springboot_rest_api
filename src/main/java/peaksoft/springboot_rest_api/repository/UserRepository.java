package peaksoft.springboot_rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.springboot_rest_api.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

}
