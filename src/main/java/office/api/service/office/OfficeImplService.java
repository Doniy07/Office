package office.api.service.office;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import office.api.dto.office.OfficeRequestDTO;
import office.api.dto.office.OfficeResponseDTO;
import office.api.entity.OfficeEntity;
import office.api.exception.BadRequestException;
import office.api.repository.OfficeRepository;
import office.api.service.employee.EmployeeImpService;
import office.api.util.ApiResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfficeImplService implements OfficeService {

    private final OfficeRepository officeRepository;
    private final EmployeeImpService employeeImpService;

    @Override
    @Transactional
    public ApiResponse<OfficeResponseDTO> save(OfficeRequestDTO request) {
        log.info("Save request: {}", request);
        OfficeEntity entity = officeRepository.save(mapToEntity(request));
        OfficeResponseDTO response = mapToResponse(entity);
        return ApiResponse.ok(response);
    }

    @Override
    public ApiResponse<OfficeResponseDTO> findById(String officeId) {
        return ApiResponse.ok(mapToResponse(getById(officeId)));
    }


    @Override
    @Transactional
    public ApiResponse<OfficeResponseDTO> update(String officeId, OfficeRequestDTO.OfficeUpdateDTO request) {
        OfficeEntity entity = getById(officeId);
        if (request.address() != null) entity.setAddress(request.address());
        if (request.companyName() != null) entity.setCompanyName(request.companyName());
        if (request.companyFloor() != null) entity.setCompanyFloor(request.companyFloor());
        entity.setUpdatedDate(LocalDateTime.now());
        officeRepository.save(entity);
        return new ApiResponse<>("Successful update", 200, false);
    }

    @Override
    public ApiResponse<?> deleteById(String officeId) {
//        int deleteStatus = officeRepository.deleteByOfficeId(officeId);
//        if (deleteStatus > 0) {
//            employeeImpService.deleteAllByOfficeId(officeId);
//            log.info("Deleted successful office with id {}", officeId);
//            return ApiResponse.ok();
//        }
//        return ApiResponse.bad("Delete failed");
        getById(officeId);
        officeRepository.deleteById(officeId);
        return new ApiResponse<>("Successful delete", 200, false);
    }

    private OfficeEntity getById(String officeId) {
        return officeRepository.findById(officeId).orElseThrow(() -> {
                    log.warn("Office with id {} not found", officeId);
                    return new BadRequestException("Office not found");
                }
        );
    }

    private OfficeEntity mapToEntity(OfficeRequestDTO request) {
        return OfficeEntity.builder()
                .address(request.address())
                .companyName(request.companyName())
                .companyFloor(request.companyFloor())
                .build();
    }

    private OfficeResponseDTO mapToResponse(OfficeEntity entity) {
        return OfficeResponseDTO.builder()
                .id(entity.getId())
                .address(entity.getAddress())
                .companyName(entity.getCompanyName())
                .companyFloor(entity.getCompanyFloor())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
