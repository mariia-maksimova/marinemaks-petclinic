package mm.infrastructure.repositories;

import mm.domain.pets.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
