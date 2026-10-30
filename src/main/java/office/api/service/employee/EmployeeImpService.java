package office.api.service.employee;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import office.api.dto.employee.EmployeeRequestDTO;
import office.api.dto.employee.EmployeeResponseDTO;
import office.api.entity.EmployeeEntity;
import office.api.exception.BadRequestException;
import office.api.repository.EmployeeRepository;
import office.api.repository.OfficeRepository;
import office.api.util.ApiResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeImpService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OfficeRepository officeRepository;

//    @Override
//    public ApiResponse<EmployeeResponseDTO> findById(String id) {
//        log.info("Find employee by id: {}", id);
//        return ApiResponse.ok(mapToResponse().apply(getById(id)));
//    }

//    @Override
//    @Transactional
//    public ApiResponse<EmployeeResponseDTO> save(EmployeeRequestDTO request) {
//        officeRepository.findById(request.employeeId());
//        employeeRepository.save(mapToEntity().apply(request));
//        return ApiResponse.ok();
//    }

//    @Override
//    @Transactional
//    public ApiResponse<EmployeeResponseDTO> update(String employeeId, EmployeeRequestDTO request) {
//        EmployeeEntity employee = getById(employeeId);
//        employee.setName(request.name());
//        employee.setLastname(request.lastname());
//        employee.setPhone(request.phone());
//        employee.setStatus(request.status());
//        employee.setUpdatedDate(LocalDateTime.now());
//        employeeRepository.save(employee);
//        log.info("Update employee with id: {}", employeeId);
//        return ApiResponse.ok(mapToResponse().apply(employee));
//    }

//    private EmployeeEntity getById(String employeeId) {
//        return employeeRepository.findByIdAndVisibleTrue(employeeId).orElseThrow(() -> {
//            log.warn("Employee with id {} not found", employeeId);
//            return new BadRequestException("Employee not found");
//        });
//    }

//    @Override
//    public ApiResponse deleteById(String employeeId) {
//        int deleteStatus = employeeRepository.deleteByEmployeeId(employeeId);
//        if (deleteStatus > 0) {
//            log.info("Employee with id {} deleted", employeeId);
//            return ApiResponse.ok();
//        }
//        return ApiResponse.bad("Deleted failed");
//    }

//    public void deleteAllByOfficeId(String officeId) {
//        List<String> employeeIdList = employeeRepository.findAllByEmployeeIdAndVisibleTrue((officeId));
//        employeeIdList.stream().forEach(employeeId -> {
//            deleteById(employeeId);
//        });
//    }

    private Function<EmployeeRequestDTO, EmployeeEntity> mapToEntity() {
        return request -> EmployeeEntity.builder()
                .name(request.name())
                .lastname(request.lastname())
                .phone(request.phone())
                .status(request.status())
                .officeId(request.employeeId())
                .build();
    }

    private Function<EmployeeEntity, EmployeeResponseDTO> mapToResponse() {
        return entity -> EmployeeResponseDTO.builder()
                .name(entity.getName())
                .lastname(entity.getLastname())
                .phone(entity.getPhone())
                .status(entity.getStatus())
                .build();
    }
}
