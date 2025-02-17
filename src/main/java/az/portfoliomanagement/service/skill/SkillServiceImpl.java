package az.portfoliomanagement.service.skill;

import az.portfoliomanagement.dto.request.SkillRequest;
import az.portfoliomanagement.dto.response.SkillResponse;
import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.entity.Skill;
import az.portfoliomanagement.exception.CustomException;
import az.portfoliomanagement.repo.PortfolioRepo;
import az.portfoliomanagement.repo.SkillRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepo skillRepo;
    private final ModelMapper modelMapper;
    private final PortfolioRepo portfolioRepo;

    @Override
    public List<SkillResponse> getAll() {
        return skillRepo
                .findAll()
                .stream()
                .map(skill -> modelMapper.map(skill, SkillResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public SkillResponse addSkill(SkillRequest skillRequest) {
        Portfolio portfolio = portfolioRepo.findByTitle(skillRequest.getPortfolioTitle())
                .orElseThrow(() -> new CustomException("Portfolio not found with title: " + skillRequest.getPortfolioTitle()));

        Skill skill = modelMapper.map(skillRequest, Skill.class);
        skill.setPortfolio(portfolio);

        Skill savedSkill = skillRepo.save(skill);

        return modelMapper.map(savedSkill, SkillResponse.class);
    }

    @Override
    public SkillResponse update(Long skillId, SkillRequest skillRequest, String portfolioTitle) {
        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new CustomException("Project not found with ID: " + skillId));

        if (!skill.getPortfolio().getTitle().equals(portfolioTitle)) {
            throw new CustomException("Project does not belong to this Portfolio");
        }

        skill.setName(skill.getName());
        skill.setLevel(skillRequest.getLevel());

        Skill updatedSkill = skillRepo.save(skill);
        return new SkillResponse(
                updatedSkill.getSkill_id(),
                updatedSkill.getLevel(),
                updatedSkill.getName());
    }

    @Override
    public void delete(Long skillId) {
        Skill skill = skillRepo.findById(skillId)
                .orElseThrow(() -> new CustomException("Skill not found"));
        skillRepo.delete(skill);
    }
}
