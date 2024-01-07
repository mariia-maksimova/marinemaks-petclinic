package mm.infrastructure.services.springdatajpa;

import lombok.AllArgsConstructor;
import mm.domain.Address;
import mm.infrastructure.repositories.AddressRepository;
import mm.infrastructure.services.AddressService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile("spring-data-jpa")
public class AddressSPJpaService implements AddressService {

    private final AddressRepository addressRepository;
    @Override
    public Set<Address> findAll() {
        Set<Address> addresses = new HashSet<>();
        addressRepository.findAll().forEach(addresses::add);
        return addresses;
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public Address save(Address object) {
        return addressRepository.save(object);
    }

    @Override
    public void delete(Address object) {
        addressRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
