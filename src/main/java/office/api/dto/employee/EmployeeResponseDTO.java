package office.api.dto.employee;

import lombok.Builder;
import office.api.enums.EmployeeStatus;

@Builder
public record EmployeeResponseDTO(
        String name,
        String lastname,
        String phone,
        EmployeeStatus status
) {}
