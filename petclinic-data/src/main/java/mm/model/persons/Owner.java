package mm.model.persons;

import mm.model.pets.Pet;

import java.util.Set;

public class Owner extends Person {
    private Set<Pet> pets;

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
