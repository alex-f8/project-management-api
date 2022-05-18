package af.projectmanagement.project;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(long id);
}
