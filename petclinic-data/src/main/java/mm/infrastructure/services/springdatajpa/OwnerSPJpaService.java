package mm.infrastructure.services.springdatajpa;

import lombok.AllArgsConstructor;
import mm.domain.persons.owners.Owner;
import mm.infrastructure.repositories.OwnerRepository;
import mm.infrastructure.repositories.PetRepository;
import mm.infrastructure.repositories.PetTypeRepository;
import mm.infrastructure.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile("spring-data-jpa")
public class OwnerSPJpaService implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PetRepository petRepository;
    private final PetTypeRepository petTypeRepository;

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner OwnerFindByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
