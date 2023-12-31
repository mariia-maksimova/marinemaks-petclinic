package mm.services.map;

import mm.model.Address;
import mm.model.persons.owners.Owner;
import mm.model.pets.Pet;
import mm.model.pets.PetType;
import mm.services.AddressService;
import mm.services.OwnerService;
import mm.services.PetService;
import mm.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;
    private final AddressService addressService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService, AddressService addressService) {
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
