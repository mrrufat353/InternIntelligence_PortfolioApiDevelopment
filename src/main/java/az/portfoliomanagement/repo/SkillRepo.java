package az.portfoliomanagement.repo;

import az.portfoliomanagement.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {
}
