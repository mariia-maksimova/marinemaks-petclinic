package mm.domain.visits;

import jakarta.persistence.*;
import mm.domain.BaseEntity;
import mm.domain.persons.vets.Vet;
import mm.domain.pets.Pet;

import java.time.LocalDateTime;

@Entity
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }
}
