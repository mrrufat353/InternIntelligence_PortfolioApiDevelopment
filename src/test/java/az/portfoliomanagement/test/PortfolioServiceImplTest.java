package az.portfoliomanagement.test;

import az.portfoliomanagement.dto.request.PortfolioCreateRequest;
import az.portfoliomanagement.dto.response.PortfolioResponse;
import az.portfoliomanagement.entity.Portfolio;
import az.portfoliomanagement.entity.User;
import az.portfoliomanagement.exception.CustomException;
import az.portfoliomanagement.repo.PortfolioRepo;
import az.portfoliomanagement.repo.UserRepo;
import az.portfoliomanagement.service.portfolio.PortfolioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceImplTest {

    @Mock
    private PortfolioRepo portfolioRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PortfolioServiceImpl portfolioService;

    private Portfolio portfolio;
    private User user;
    private PortfolioCreateRequest portfolioCreateRequest;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");

        portfolio = new Portfolio();
        portfolio.setTitle("Test Portfolio");
        portfolio.setUser(user);

        portfolioCreateRequest = new PortfolioCreateRequest();
        portfolioCreateRequest.setTitle("Test Portfolio");
        portfolioCreateRequest.setUserEmail("test@example.com");
    }

    @Test
    void shouldFindByTitle() {
        when(portfolioRepo.findByTitle("Test Portfolio")).thenReturn(Optional.of(portfolio));

        PortfolioResponse expectedResponse = new PortfolioResponse();
        expectedResponse.setTitle("Test Portfolio");

        when(modelMapper.map(portfolio, PortfolioResponse.class)).thenReturn(expectedResponse);

        PortfolioResponse response = portfolioService.findByTitle("Test Portfolio");

        assertNotNull(response);
        assertEquals("Test Portfolio", response.getTitle());
        verify(portfolioRepo, times(1)).findByTitle("Test Portfolio");
    }

    @Test
    void shouldThrowExceptionWhenPortfolioNotFound() {
        when(portfolioRepo.findByTitle("Non-existent")).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> portfolioService.findByTitle("Non-existent"));
    }

    @Test
    void shouldCreatePortfolio() {
        when(portfolioRepo.existsByTitle("Test Portfolio")).thenReturn(false);
        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));
        when(portfolioRepo.save(Mockito.any(Portfolio.class))).thenReturn(portfolio);

        PortfolioResponse expectedResponse = new PortfolioResponse();
        expectedResponse.setTitle("Test Portfolio");

        when(modelMapper.map(portfolio, PortfolioResponse.class)).thenReturn(expectedResponse);

        PortfolioResponse response = portfolioService.create(portfolioCreateRequest);

        assertNotNull(response);
        assertEquals("Test Portfolio", response.getTitle());
        verify(portfolioRepo, times(1)).save(Mockito.any(Portfolio.class));
    }

    @Test
    void shouldThrowExceptionWhenCreatingDuplicatePortfolio() {
        when(portfolioRepo.existsByTitle("Test Portfolio")).thenReturn(true);
        assertThrows(CustomException.class, () -> portfolioService.create(portfolioCreateRequest));
    }

    @Test
    void shouldDeletePortfolio() {
        when(portfolioRepo.findByTitle("Test Portfolio")).thenReturn(Optional.of(portfolio));
        doNothing().when(portfolioRepo).delete(portfolio);

        portfolioService.delete("Test Portfolio");

        verify(portfolioRepo, times(1)).delete(portfolio);
    }

    @Test
    void shouldThrowExceptionWhenDeletingNonExistentPortfolio() {
        when(portfolioRepo.findByTitle("Non-existent")).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> portfolioService.delete("Non-existent"));
    }
}
