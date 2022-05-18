package af.projectmanagement.project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ProjectDTORopository extends JpaRepository<ProjectDTO, Long> {
    Optional<ProjectDTO> findById(long id);
}
