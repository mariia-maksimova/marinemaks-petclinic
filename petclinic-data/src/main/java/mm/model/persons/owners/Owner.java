package mm.model.persons.owners;

import mm.model.Address;
import mm.model.persons.Person;
import mm.model.pets.Pet;

import java.util.HashSet;
import java.util.Set;

public class Owner extends Person {

    private Address address;
    private String phoneNumber;
    private Set<Pet> pets = new HashSet<>();

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
