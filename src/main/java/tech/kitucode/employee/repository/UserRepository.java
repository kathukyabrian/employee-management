package tech.kitucode.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kitucode.employee.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByIdNumber(String idNumber);
}
