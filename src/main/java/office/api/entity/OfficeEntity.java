package office.api.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "office")
public class OfficeEntity extends BaseEntity{

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "company_floor", nullable = false)
    private String companyFloor;
}
