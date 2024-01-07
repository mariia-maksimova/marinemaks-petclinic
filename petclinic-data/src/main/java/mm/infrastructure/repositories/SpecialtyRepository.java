package mm.infrastructure.repositories;

import mm.domain.persons.vets.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
