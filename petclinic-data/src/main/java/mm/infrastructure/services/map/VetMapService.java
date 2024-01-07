package mm.infrastructure.services.map;

import mm.infrastructure.services.VetService;
import mm.domain.persons.vets.Vet;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyMapService specialtyService;

    public VetMapService(SpecialtyMapService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {

        if (!object.getSpecialties().isEmpty()) {
            object.getSpecialties().forEach(specialty -> {
                if (specialty.getId() == null) {
                    specialty.setId(specialtyService.save(specialty).getId());
                }
            });
        } else {
            throw new RuntimeException("Vet must have at least one specialty");
        }

        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
