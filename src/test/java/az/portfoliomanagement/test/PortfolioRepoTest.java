package az.portfoliomanagement.test;

import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.entity.User;
import az.portfoliomanagement.repo.PortfolioRepo;
import az.portfoliomanagement.repo.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PortfolioRepoTest {

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private UserRepo userRepo;

    @Test
    void testSaveAndFindByTitle() {
        // Given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setUsername("TestUser");
        user = userRepo.save(user);

        Portfolio portfolio = Portfolio.builder()
                .title("Test Portfolio")
                .description("This is a test portfolio")
                .user(user)
                .build();

        portfolioRepo.save(portfolio);

        // When
        Optional<Portfolio> foundPortfolio = portfolioRepo.findByTitle("Test Portfolio");

        // Then
        assertTrue(foundPortfolio.isPresent());
        assertEquals("Test Portfolio", foundPortfolio.get().getTitle());
        assertEquals("This is a test portfolio", foundPortfolio.get().getDescription());
    }

    @Test
    void testExistsByTitle() {
        // Given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setUsername("TestUser");
        user = userRepo.save(user);

        Portfolio portfolio = Portfolio.builder()
                .title("Unique Portfolio")
                .description("This portfolio should be unique")
                .user(user)
                .build();

        portfolioRepo.save(portfolio);

        // When
        boolean exists = portfolioRepo.existsByTitle("Unique Portfolio");

        // Then
        assertTrue(exists);
    }

    @Test
    void testFindByTitle_NotFound() {
        // When
        Optional<Portfolio> foundPortfolio = portfolioRepo.findByTitle("Non-Existent Portfolio");

        // Then
        assertFalse(foundPortfolio.isPresent());
    }
}

