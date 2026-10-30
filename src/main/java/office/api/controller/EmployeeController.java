package office.api.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import office.api.dto.employee.EmployeeRequestDTO;
import office.api.dto.employee.EmployeeResponseDTO;
import office.api.service.employee.EmployeeService;
import office.api.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmployeeController {

    EmployeeService employeeService;

    public static final String CREATE = "/create";
    public static final String FETCH_BY_ID = "/{id}";
    public static final String UPDATE = "/update/{id}";
    public static final String DELETE = "/delete/{id}";

    @PostMapping(CREATE)
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> create(
            @Valid @RequestBody EmployeeRequestDTO request) {
        return ResponseEntity.ok(employeeService.save(request));
    }

    @GetMapping(FETCH_BY_ID)
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> getById(
            @PathVariable("id") String officeId) {
        log.info("get book by id: {}", officeId);
        return ResponseEntity.ok(employeeService.findById(officeId));
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ApiResponse<EmployeeResponseDTO>> update(
            @PathVariable("id") String officeId,
            @Valid @RequestBody EmployeeRequestDTO request) {
        return ResponseEntity.ok(employeeService.update(officeId, request));
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<ApiResponse> delete(
            @PathVariable("id") String bookId) {
        log.info("delete employee with id: {}", bookId);
        return ResponseEntity.ok(employeeService.deleteById(bookId));
    }

}
