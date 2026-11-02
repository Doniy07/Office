package office.api.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    List<EmployeeEntity> employees;
}
