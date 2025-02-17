package az.portfoliomanagement.controller;

import az.portfoliomanagement.dto.request.ExperienceRequest;
import az.portfoliomanagement.dto.response.ExperienceResponse;
import az.portfoliomanagement.service.experience.ExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    @PostMapping("/add")
    public ResponseEntity<ExperienceResponse> addExperience(ExperienceRequest experienceRequest) {
        return new ResponseEntity<>(experienceService.addExperience(experienceRequest), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExperienceResponse>> getAll() {
        return new ResponseEntity<>(experienceService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{experience_id}")
    public ResponseEntity<ExperienceResponse> update(String title, @PathVariable Long experience_id,
                                                     @Valid @RequestBody ExperienceRequest experienceRequest) {
    return new ResponseEntity<>(experienceService.update(title, experience_id, experienceRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{experience_id}")
    public void delete(@PathVariable Long experience_id){
        experienceService.delete(experience_id);
    }
}
