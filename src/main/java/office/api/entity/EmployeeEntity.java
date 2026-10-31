package office.api.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import office.api.enums.EmployeeStatus;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeEntity extends BaseEntity {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "lastname", nullable = false)
    String lastname;

    @Column(name = "phone", nullable = false)
    String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status", nullable = false)
    EmployeeStatus status;

    @Column(name = "office_id")
    String officeId;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "office_id", insertable = false, updatable = false)
    OfficeEntity office;
}
