package mm.bootstrap;

import mm.model.Address;
import mm.model.persons.owners.Owner;
import mm.model.persons.vets.Vet;
import mm.model.pets.Pet;
import mm.model.pets.PetType;
import mm.services.OwnerService;
import mm.services.PetTypeService;
import mm.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started loading data...");

        PetType dog = new PetType();
        dog.setName("Dog");

        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");

        PetType savedCatType = petTypeService.save(cat);

        PetType parrot = new PetType();
        parrot.setName("Parrot");

        PetType savedParrotType = petTypeService.save(parrot);

        PetType hamster = new PetType();
        hamster.setName("Hamster");

        PetType savedHamsterType = petTypeService.save(hamster);

        System.out.println("Loaded Pet Types.");

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        Address address1 = new Address();
        address1.setAddressLine1("123 Oxford Road");
        address1.setAddressLine2("Apt. 123");
        address1.setCity("Reading");
        address1.setCounty("Berkshire");
        address1.setPostcode("12345");

        owner1.setAddress(address1);
        owner1.setPhoneNumber("1234567890");

        Pet nika = new Pet();
        nika.setName("Nika");
        nika.setPetType(savedCatType);
        nika.setOwner(owner1);
        nika.setBirthDate(LocalDate.of(2023, Month.MARCH,1));

        owner1.getPets().add(nika);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");

        Address address2 = new Address();
        address2.setAddressLine1("321 London Road");
        address2.setAddressLine2("Apt. 321");
        address2.setCity("Oxford");
        address2.setCounty("Oxfordshire");
        address2.setPostcode("54321");

        owner2.setAddress(address2);
        owner2.setPhoneNumber("0987654321");

        Pet milo = new Pet();
        milo.setName("Milo");
        milo.setPetType(savedDogType);
        milo.setOwner(owner2);
        milo.setBirthDate(LocalDate.of(2022, Month.JANUARY,1));

        owner2.getPets().add(milo);

        ownerService.save(owner2);

        System.out.println("Loaded Owners.");

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets.");

        System.out.println("Finished loading data.");

    }
}
