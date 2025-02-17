package az.portfoliomanagement.repo;

import az.portfoliomanagement.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfolioRepo extends JpaRepository<Portfolio, Long> {

    boolean existsByTitle(String title);

    Optional<Portfolio> findByTitle(String portfolioTitle);
}
