package office.api.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import office.api.dto.office.OfficeRequestDTO;
import office.api.dto.office.OfficeResponseDTO;
import office.api.service.office.OfficeService;
import office.api.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/office")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class OfficeController {

    OfficeService officeService;

    public static final String CREATE = "/create";
    public static final String FETCH_BY_ID = "/{id}";
    public static final String UPDATE = "/update/{id}";
    public static final String DELETE = "/delete/{id}";

    @PostMapping(CREATE)
    public ResponseEntity<ApiResponse<OfficeResponseDTO>> create(
            @Valid @RequestBody OfficeRequestDTO request) {
        log.info("Create office request: {}", request);
        return ResponseEntity.ok(officeService.save(request));
    }

//    @GetMapping(FETCH_BY_ID)
//    public ResponseEntity<ApiResponse<OfficeResponseDTO>> getById(
//            @PathVariable("id") String officeId) {
//        log.info("get book by id: {}", officeId);
//        return ResponseEntity.ok(officeService.findById(officeId));
//    }

//    @PutMapping(UPDATE)
//    public ResponseEntity<ApiResponse<OfficeResponseDTO>> update(
//            @PathVariable("id") String officeId,
//            @Valid @RequestBody OfficeRequestDTO request) {
//        return ResponseEntity.ok(officeService.update(officeId, request));
//    }

//    @DeleteMapping(DELETE)
//    public ResponseEntity<ApiResponse> delete(
//            @PathVariable("id") String bookId) {
//        log.info("delete minivan with id: {}", bookId);
//        return ResponseEntity.ok(officeService.deleteById(bookId));
//    }
}
