package mm.services;

import mm.model.persons.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner OwnerFindByLastName(String lastName);
}
