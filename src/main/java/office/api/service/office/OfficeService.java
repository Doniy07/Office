package office.api.service.office;

import office.api.dto.office.OfficeRequestDTO;
import office.api.dto.office.OfficeResponseDTO;
import office.api.util.ApiResponse;

public interface OfficeService {
//    ApiResponse<OfficeResponseDTO> findById(String id);

    ApiResponse<OfficeResponseDTO> save(OfficeRequestDTO request);

//    ApiResponse<OfficeResponseDTO> update(String officeId, OfficeRequestDTO request);

//    ApiResponse deleteById(String employeeId);

}
