package mm.domain.persons.vets;

import jakarta.persistence.*;
import lombok.*;
import mm.domain.persons.Person;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vets")
public class Vet extends Person {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vets_specialties",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();

    @Builder
    public Vet(String firstName, String lastName, Set<Specialty> specialties) {
        super(firstName, lastName);
        this.specialties = specialties;
    }
}
