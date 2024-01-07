package mm.infrastructure.services;

import mm.domain.persons.owners.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner OwnerFindByLastName(String lastName);
}
