package mm.domain.visits;

import jakarta.persistence.*;
import lombok.*;
import mm.domain.BaseEntity;
import mm.domain.persons.vets.Vet;
import mm.domain.pets.Pet;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "visits")
public class Visit extends BaseEntity {
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;
}
