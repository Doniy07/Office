package office.api.service.office;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OfficeImplService implements OfficeService {

    OfficeRepository officeRepository;
    EmployeeImpService employeeImpService;

    @Override
    public ApiResponse<OfficeResponseDTO> findById(String officeId) {
        log.info("Find office by id: {}", officeId);
        return ApiResponse.ok(mapToResponse().apply(getById(officeId)));
    }

    @Override
    @Transactional
    public ApiResponse<OfficeResponseDTO> save(OfficeRequestDTO request) {
        officeRepository.save(mapToEntity().apply(request));
        log.info("Save request: {}", request);
        return ApiResponse.ok();
    }

    @Override
    @Transactional
    public ApiResponse<OfficeResponseDTO> update(String officeId, OfficeRequestDTO request) {
        OfficeEntity entity = getById(officeId);
        entity.setAddress(request.address());
        entity.setCompanyName(request.companyName());
        entity.setCompanyFloor(request.companyFloor());
        entity.setUpdatedDate(LocalDateTime.now());
        officeRepository.save(entity);
        log.info("Updated successful office with name {}", request.companyName());
        return ApiResponse.ok(mapToResponse().apply(entity));
    }

    @Override
    public ApiResponse deleteById(String officeId) {

        int deleteStatus = officeRepository.deleteByOfficeId(officeId);
        if (deleteStatus > 0) {
            employeeImpService.deleteAllByOfficeId(officeId);
            log.info("Deleted successful office with id {}", officeId);
            return ApiResponse.ok();
        }
        return ApiResponse.bad("Delete failed");
    }

    private OfficeEntity getById(String officeId) {
        return officeRepository.findByIdAndVisibleTrue(officeId).orElseThrow(() -> {
                    log.warn("Office with id {} not found", officeId);
                    return new BadRequestException("Office not found");
                }
        );
    }


    private Function<OfficeRequestDTO, OfficeEntity> mapToEntity() {
        return request -> OfficeEntity.builder()
                .address(request.address())
                .companyName(request.companyName())
                .companyFloor(request.companyFloor())
                .build();
    }

    private Function<OfficeEntity, OfficeResponseDTO> mapToResponse() {
        return entity -> OfficeResponseDTO.builder()
                .address(entity.getAddress())
                .companyName(entity.getCompanyName())
                .companyFloor(entity.getCompanyFloor())
                .build();
    }
}
