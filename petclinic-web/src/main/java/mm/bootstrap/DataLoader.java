package mm.bootstrap;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mm.domain.Address;
import mm.domain.persons.owners.Owner;
import mm.domain.persons.vets.Specialty;
import mm.domain.persons.vets.Vet;
import mm.domain.pets.Pet;
import mm.domain.pets.PetType;
import mm.domain.visits.Visit;
import mm.infrastructure.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final AddressService addressService;
    private final PetService petService;
    private final VisitService visitService;

    @Override
    public void run(String... args) throws Exception {

        int countPetTypes = petTypeService.findAll().size();
        if (countPetTypes == 0) {
            loadMockData();
        }
    }

    private void loadMockData() {
        log.info("Started loading data...");

        PetType savedDogType = savePetType("Dog");
        PetType savedCatType = savePetType("Cat");
        PetType savedParrotType = savePetType("Parrot");
        PetType savedHamsterType = savePetType("Hamster");

        log.info("Loaded Pet Types.");

        // Setting up Specialties
        Specialty savedRadiology = saveSpecialty("Radiology");
        Specialty savedSurgery = saveSpecialty("Surgery");
        Specialty savedDentistry = saveSpecialty("Dentistry");

        log.info("Loaded Specialties.");

        // Setting up Owners
        Address address1 = saveAddress(
                "123 London Road",
                "Apt. 123",
                "London",
                "London",
                "12345"
        );

        Owner michael = saveOwner(
                "Michael",
                "Weston",
                address1,
                "1234567890"
        );
        Pet nika = createPet("Nika", savedCatType, michael, LocalDate.of(2023, Month.MARCH,1));

        Address address2 = saveAddress(
                "321 London Road",
                "Apt. 321",
                "Oxford",
                "Oxfordshire",
                "54321"
        );

        Owner fiona = saveOwner(
                "Fiona",
                "Glenanne",
                address2,
                "0987654321"
        );
        Pet milo = createPet("Milo", savedDogType, fiona, LocalDate.of(2022, Month.JANUARY,1));

        log.info("Loaded Owners.");

        // Setting up Vets
        Vet vetSam = saveVet("Sam", "Axe", Set.of(savedRadiology, savedSurgery));
        Vet vetJessie = saveVet("Jessie", "Porter", Set.of(savedDentistry));

        log.info("Loaded Vets.");

        // Setting up Visits
        Visit miloVisit1 = saveVisit(milo, LocalDateTime.of(2021, Month.JANUARY, 1, 12, 0),
                "Sneezy Dog", vetSam);
        Visit miloVisit2 = saveVisit(milo, LocalDateTime.of(2021, Month.JANUARY, 2, 12, 0),
                "Sneezy Dog: return", vetJessie);

        Visit nikaVisit1 = saveVisit(nika, LocalDateTime.of(2021, Month.MARCH, 1, 12, 0),
                "Sneezy Kitty", vetSam);

        log.info("Loaded Visits.");

        log.info("Finished loading data.");
    }

    private Visit saveVisit(Pet pet, LocalDateTime dateTime, String description, Vet vet) {
        Visit visit = Visit.builder()
                .pet(pet)
                .dateTime(dateTime)
                .description(description)
                .vet(vet)
                .build();
        return visitService.save(visit);
    }

    private Vet saveVet(String firstName, String lastName, Set<Specialty> specialtySet) {
        Vet vet1 = Vet.builder()
                .firstName(firstName)
                .lastName(lastName)
                .specialties(specialtySet)
                .build();

        return vetService.save(vet1);
    }

    private Owner saveOwner(
            String firstName,
            String lastName,
            Address address,
            String phoneNumber
    ) {
        Owner owner = Owner.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(address)
                .phoneNumber(phoneNumber)
                .pets(new HashSet<>())
                .build();

        return ownerService.save(owner);
    }

    private Pet createPet(String name, PetType petType, Owner owner, LocalDate birthDate) {
        Pet pet = Pet.builder()
                .name(name)
                .petType(petType)
                .owner(owner)
                .birthDate(birthDate)
                .build();

        owner.getPets().add(pet);

        return petService.save(pet);
    }

    private Address saveAddress(
            String addressLine1,
            String addressLine2,
            String city,
            String county,
            String postcode
    ) {
        Address address = Address.builder()
                .addressLine1(addressLine1)
                .addressLine2(addressLine2)
                .city(city)
                .county(county)
                .postcode(postcode)
                .build();

        return addressService.save(address);
    }

    private Specialty saveSpecialty(String description) {
        Specialty specialty = Specialty.builder().description(description).build();

        return specialtyService.save(specialty);
    }

    private PetType savePetType(String name) {
        PetType petType = PetType.builder().name(name).build();

        return petTypeService.save(petType);
    }
}
