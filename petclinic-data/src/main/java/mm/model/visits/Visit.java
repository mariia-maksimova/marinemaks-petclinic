package mm.model.visits;

import mm.model.BaseEntity;
import mm.model.pets.Pet;

import java.time.LocalDateTime;

public class Visit extends BaseEntity {

    private LocalDateTime dateTime;
    private String description;
    private Pet pet;

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
}
