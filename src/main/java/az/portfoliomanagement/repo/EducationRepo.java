package az.portfoliomanagement.repo;

import az.portfoliomanagement.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationRepo extends JpaRepository<Education, Long> {
}
