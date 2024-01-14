package mm.domain.pets;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import mm.domain.BaseEntity;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "types")
public class PetType extends BaseEntity {
    @Column(name = "name")
    private String name;
}
