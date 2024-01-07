package mm.infrastructure.repositories;

import mm.domain.pets.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
