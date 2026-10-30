package office.api.dto.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import office.api.enums.EmployeeStatus;

/**
 * DTO for {@link office.api.entity.EmployeeEntity}
 */
@Builder
public record EmployeeRequestDTO(
        @Size(min = 3, max = 20) @NotBlank
        String name,
        @Size(min = 3, max = 20) @NotBlank
        String lastname,
        @NotBlank
        String phone,
        @NotNull
        EmployeeStatus status,
        @NotBlank
        String employeeId
){}