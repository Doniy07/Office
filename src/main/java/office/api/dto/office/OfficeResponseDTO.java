package office.api.dto.office;

import lombok.Builder;

@Builder
public record OfficeResponseDTO(
        String address,
        String companyName,
        String companyFloor
) {
}
