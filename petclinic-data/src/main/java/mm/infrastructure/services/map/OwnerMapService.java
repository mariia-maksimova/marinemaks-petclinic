package mm.infrastructure.services.map;

import mm.infrastructure.services.PetService;
import mm.domain.Address;
import mm.domain.persons.owners.Owner;
import mm.domain.pets.Pet;
import mm.domain.pets.PetType;
import mm.infrastructure.services.AddressService;
import mm.infrastructure.services.OwnerService;
import mm.infrastructure.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;
    private final AddressService addressService;

    public OwnerMapService(PetTypeService petTypeService, PetService petService, AddressService addressService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.addressService = addressService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() != null) {
                        if (pet.getPetType().getId() == null) {
                            PetType savedPetType = petTypeService.save(pet.getPetType());
                            pet.setPetType(savedPetType);
                        }
                    } else {
                        throw new RuntimeException("Pet Type is required");
                    }
                    if (pet.getId() == null) {
                        Pet savedPet = petService.save(pet);
                        pet.setId(savedPet.getId());
                    }
                });
            }
            if (object.getAddress() != null) {
                if (object.getAddress().getId() == null) {
                    Address savedAddress = addressService.save(object.getAddress());
                    object.setAddress(savedAddress);
                }
            }
            return super.save(object);
        }
        return null;
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner OwnerFindByLastName(String lastName) {
        return null; //TODO: implement this
    }
}
