package marinemaks.services;

import marinemaks.model.persons.Owner;

import java.util.Set;

public interface OwnerService {
    Owner OwnerFindByLastName(String lastName);
    Owner findById(Long id);
    Owner save(Owner owner);
    Set<Owner> findAll();
}
