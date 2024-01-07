package mm.bootstrap;

import mm.domain.Address;
import mm.domain.persons.owners.Owner;
import mm.domain.persons.vets.Specialty;
import mm.domain.persons.vets.Vet;
import mm.domain.pets.Pet;
import mm.domain.pets.PetType;
import mm.infrastructure.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final AddressService addressService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, AddressService addressService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {

        int countPetTypes = petTypeService.findAll().size();
        if (countPetTypes == 0) {
            loadMockData();
        }
    }

    private void loadMockData() {
        System.out.println("Started loading data...");

        PetType savedDogType = savePetType("Dog");
        PetType savedCatType = savePetType("Cat");
        PetType savedParrotType = savePetType("Parrot");
        PetType savedHamsterType = savePetType("Hamster");

        System.out.println("Loaded Pet Types.");

        // Setting up Specialties
        Specialty savedRadiology = saveSpecialty("Radiology");
        Specialty savedSurgery = saveSpecialty("Surgery");
        Specialty savedDentistry = saveSpecialty("Dentistry");

        System.out.println("Loaded Specialties.");

        // Setting up Owners
        Address address1 = saveAddress(
                "123 London Road",
                "Apt. 123",
                "London",
                "London",
                "12345"
        );
        Pet nika = createPet("Nika", savedCatType, null, LocalDate.of(2023, Month.MARCH,1));
        saveOwner(
                "Michael",
                "Weston",
                address1,
                "1234567890",
                nika
        );

        Address address2 = saveAddress(
                "321 London Road",
                "Apt. 321",
                "Oxford",
                "Oxfordshire",
                "54321"
        );
        Pet milo = createPet("Milo", savedDogType, null, LocalDate.of(2022, Month.JANUARY,1));
        saveOwner(
                "Fiona",
                "Glenanne",
                address2,
                "0987654321",
                milo
        );

        System.out.println("Loaded Owners.");

        // Setting up Vets
        saveVet("Sam", "Axe", Set.of(savedRadiology, savedSurgery));
        saveVet("Jessie", "Porter", Set.of(savedDentistry));

        System.out.println("Loaded Vets.");

        System.out.println("Finished loading data.");
    }

    private void saveVet(String firstName, String lastName, Set<Specialty> specialtySet) {
        Vet vet1 = new Vet();
        vet1.setFirstName(firstName);
        vet1.setLastName(lastName);
        vet1.getSpecialties().addAll(specialtySet);

        vetService.save(vet1);
    }

    private void saveOwner(
            String firstName,
            String lastName,
            Address address,
            String phoneNumber,
            Pet pet
    ) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);

        owner.setAddress(address);
        owner.setPhoneNumber(phoneNumber);

        pet.setOwner(owner);
        owner.getPets().add(pet);

        ownerService.save(owner);
    }

    private Pet createPet(String name, PetType petType, Owner owner, LocalDate birthDate) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setPetType(petType);
        pet.setOwner(owner);
        pet.setBirthDate(birthDate);

        return pet;
    }

    private Address saveAddress(
            String addressLine1,
            String addressLine2,
            String city,
            String county,
            String postcode
    ) {
        Address address = new Address();
        address.setAddressLine1(addressLine1);
        address.setAddressLine2(addressLine2);
        address.setCity(city);
        address.setCounty(county);
        address.setPostcode(postcode);

        return addressService.save(address);
    }

    private Specialty saveSpecialty(String description) {
        Specialty specialty = new Specialty();
        specialty.setDescription(description);

        return specialtyService.save(specialty);
    }

    private PetType savePetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);

        return petTypeService.save(petType);
    }
}
