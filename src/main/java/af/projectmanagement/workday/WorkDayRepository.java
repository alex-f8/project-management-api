package af.projectmanagement.workday;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {

    @Override
    Optional<WorkDay> findById(Long id);

    List<WorkDay> findByPidAndUid(Long pid, Long uid);

    List<WorkDay> findByUidOrderByWorkDayAsc(Long uid);
}
