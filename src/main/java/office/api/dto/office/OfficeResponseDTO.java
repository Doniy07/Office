package office.api.dto.office;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OfficeResponseDTO(
        String id,
        String address,
        String companyName,
        String companyFloor,
        LocalDateTime createdDate
) {
}
