package mm.infrastructure.repositories;

import mm.domain.persons.vets.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
