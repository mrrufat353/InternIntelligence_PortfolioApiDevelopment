package az.portfoliomanagement.controller;

import az.portfoliomanagement.dto.request.SkillRequest;
import az.portfoliomanagement.dto.response.SkillResponse;
import az.portfoliomanagement.service.skill.SkillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    @PostMapping("/add")
    public ResponseEntity<SkillResponse> addSkill(SkillRequest skillRequest) {
        return new ResponseEntity<>(skillService.addSkill(skillRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<SkillResponse>> getAll() {
        return new ResponseEntity<>(skillService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/update/{skill_id}")
    public ResponseEntity<SkillResponse> update(@PathVariable Long skill_id,
                                                @Valid @RequestBody SkillRequest skillRequest,
                                                String portfolioTitle) {
        return new ResponseEntity<>(skillService.update(skill_id, skillRequest, portfolioTitle), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{skill_id}")
    public void delete(@PathVariable Long skill_id){
        skillService.delete(skill_id);
    }
}
