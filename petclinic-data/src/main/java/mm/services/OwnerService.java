package mm.services;

import mm.model.persons.owners.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner OwnerFindByLastName(String lastName);
}
