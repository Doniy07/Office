package office.api.service.employee;

import office.api.dto.employee.EmployeeRequestDTO;
import office.api.dto.employee.EmployeeResponseDTO;
import office.api.util.ApiResponse;
import org.springframework.http.HttpStatus;


public interface EmployeeService {
    ApiResponse<EmployeeResponseDTO> findById(String id);

    ApiResponse<EmployeeResponseDTO> save(EmployeeRequestDTO request);
    ApiResponse<EmployeeResponseDTO> update(String employeeId, EmployeeRequestDTO request);

    ApiResponse deleteById(String employeeId);


}
