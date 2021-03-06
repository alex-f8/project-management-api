package af.projectmanagement.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long id);

    List<User> findByName(String name);

    List<User> findBySurname(String surname);

}
