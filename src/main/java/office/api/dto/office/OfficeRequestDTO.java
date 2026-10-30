package office.api.dto.office;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record OfficeRequestDTO(
        @NotBlank String address,
        @Size(min = 3, max = 50) @NotBlank String companyName,
        @NotNull String companyFloor
) {}