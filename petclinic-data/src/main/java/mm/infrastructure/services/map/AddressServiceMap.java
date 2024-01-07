package mm.infrastructure.services.map;

import mm.domain.Address;
import mm.infrastructure.services.AddressService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AddressServiceMap extends AbstractMapService<Address, Long> implements AddressService {
    @Override
    public Set<Address> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Address object) {
        super.delete(object);
    }

    @Override
    public Address save(Address object) {
        return super.save(object);
    }

    @Override
    public Address findById(Long id) {
        return super.findById(id);
    }
}
