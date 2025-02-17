package az.portfoliomanagement.controller;

import az.portfoliomanagement.dto.request.EducationRequest;
import az.portfoliomanagement.dto.response.EducationResponse;
import az.portfoliomanagement.service.education.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/education")
public class EducationController {

    private final EducationService educationService;

    @PostMapping("/add")
    public ResponseEntity<EducationResponse> addEdu(@Valid @RequestBody EducationRequest educationRequest) {
        return new ResponseEntity<>(educationService.addEdu(educationRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EducationResponse>> getAll() {
        return new ResponseEntity<>(educationService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{education_id}")
    public ResponseEntity<EducationResponse> update(String title, @PathVariable Long education_id,
                                                    @Valid @RequestBody EducationRequest educationRequest) {
        return new ResponseEntity<>(educationService.update(title, education_id, educationRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{education_id}")
    public void delete(@PathVariable Long education_id) {
        educationService.delete(education_id);
    }
}
