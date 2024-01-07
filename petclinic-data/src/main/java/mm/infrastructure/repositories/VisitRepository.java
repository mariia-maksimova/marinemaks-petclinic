package mm.infrastructure.repositories;

import mm.domain.visits.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
