package office.api.dto.employee;

import lombok.Builder;
import office.api.enums.EmployeeStatus;

import java.time.LocalDateTime;

@Builder
public record EmployeeResponseDTO(
        String id,
        String name,
        String lastname,
        String phone,
        EmployeeStatus status,
        LocalDateTime createdDate,
        String officeId
) {
}
