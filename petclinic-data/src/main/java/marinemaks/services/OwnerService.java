package marinemaks.services;

import marinemaks.model.persons.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner OwnerFindByLastName(String lastName);
}
