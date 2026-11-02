package office.api.service.employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import office.api.dto.employee.EmployeeRequestDTO;
import office.api.dto.employee.EmployeeResponseDTO;
import office.api.entity.EmployeeEntity;
import office.api.exception.BadRequestException;
import office.api.repository.EmployeeRepository;
import office.api.service.office.OfficeService;
import office.api.util.ApiResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeImpService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final OfficeService officeService;

    @Override
    public ApiResponse<EmployeeResponseDTO> findById(String id) {
        log.info("Find employee by id: {}", id);
        return ApiResponse.ok(mapToResponse(getById(id)));
    }

    @Override
//    @Transactional
    public ApiResponse<EmployeeResponseDTO> save(EmployeeRequestDTO request) {
        log.info("Office ", request.officeId());
        officeService.findById(request.officeId());
        EmployeeEntity entity = employeeRepository.save(mapToEntity(request));
        return ApiResponse.ok(mapToResponse(entity));
    }

    @Override
    public ApiResponse<EmployeeResponseDTO> update(String employeeId, EmployeeRequestDTO.EmployeeUpdateDTO request) {
        EmployeeEntity employee = getById(employeeId);
        if (request.name() != null) employee.setName(request.name());
        if (request.lastname() != null) employee.setLastname(request.lastname());
        if (request.phone() != null) employee.setPhone(request.phone());
        if (request.status() != null) employee.setStatus(request.status());
//        if(request != null) employee.setUpdatedDate(LocalDateTime.now());
        employeeRepository.save(employee);
        return new ApiResponse<>("Successful update", 200, false);
    }

    @Override
    public ApiResponse<?> deleteById(String employeeId) {
        getById(employeeId);
        employeeRepository.deleteById(employeeId);
        return new ApiResponse<>("Successful delete", 200, false);
    }

    private EmployeeEntity getById(String employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> {
            log.warn("Employee with id {} not found", employeeId);
            return new BadRequestException("Employee not found");
        });
    }

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

    private EmployeeEntity mapToEntity(EmployeeRequestDTO request) {
        return EmployeeEntity.builder()
                .name(request.name())
                .lastname(request.lastname())
                .phone(request.phone())
                .status(request.status())
                .officeId(request.officeId())
                .build();
    }

    private EmployeeResponseDTO mapToResponse(EmployeeEntity entity) {
        return EmployeeResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .lastname(entity.getLastname())
                .phone(entity.getPhone())
                .status(entity.getStatus())
                .createdDate(entity.getCreatedDate())
                .officeId(entity.getOfficeId())
                .build();
    }
}
