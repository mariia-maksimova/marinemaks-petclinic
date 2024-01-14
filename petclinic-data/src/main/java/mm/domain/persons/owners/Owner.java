package mm.domain.persons.owners;

import jakarta.persistence.*;
import lombok.*;
import mm.domain.Address;
import mm.domain.pets.Pet;
import mm.domain.persons.Person;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owners")
public class Owner extends Person {
    @ManyToOne
    private Address address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();

    @Builder
    public Owner(String firstName, String lastName, Address address, String phoneNumber, Set<Pet> pets) {
        super(firstName, lastName);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.pets = pets;
    }
}
