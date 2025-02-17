package az.portfoliomanagement.test;

import az.portfoliomanagement.dto.request.PortfolioCreateRequest;
import az.portfoliomanagement.dto.request.PortfolioRequest;
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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setEmail("test@example.com");

        portfolio = Portfolio.builder()
                .title("Test Portfolio")
                .description("Test Description")
                .user(user)
                .build();
    }

    @Test
    void testFindByTitle_Success() {
        // Given
        when(portfolioRepo.findByTitle("Test Portfolio")).thenReturn(Optional.of(portfolio));

        PortfolioResponse expectedResponse = new PortfolioResponse();
        expectedResponse.setTitle("Test Portfolio");
        expectedResponse.setDescription("Test Description");

        when(modelMapper.map(portfolio, PortfolioResponse.class)).thenReturn(expectedResponse);

        // When
        PortfolioResponse response = portfolioService.findByTitle("Test Portfolio");

        // Then
        assertNotNull(response);
        assertEquals("Test Portfolio", response.getTitle());
        assertEquals("Test Description", response.getDescription());
    }

    @Test
    void testFindByTitle_NotFound() {
        // Given
        when(portfolioRepo.findByTitle("Non Existent")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CustomException.class, () -> portfolioService.findByTitle("Non Existent"));
    }

    @Test
    void testCreatePortfolio_Success() {
        // Given
        PortfolioCreateRequest request = new PortfolioCreateRequest();
        request.setTitle("New Portfolio");
        request.setDescription("New Description");
        request.setUserEmail("test@example.com");

        when(portfolioRepo.existsByTitle("New Portfolio")).thenReturn(false);
        when(userRepo.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        Portfolio savedPortfolio = Portfolio.builder()
                .title("New Portfolio")
                .description("New Description")
                .user(user)
                .build();

        when(portfolioRepo.save(any(Portfolio.class))).thenReturn(savedPortfolio);

        PortfolioResponse expectedResponse = new PortfolioResponse();
        expectedResponse.setTitle("New Portfolio");
        expectedResponse.setDescription("New Description");

        when(modelMapper.map(savedPortfolio, PortfolioResponse.class)).thenReturn(expectedResponse);

        // When
        PortfolioResponse response = portfolioService.create(request);

        // Then
        assertNotNull(response);
        assertEquals("New Portfolio", response.getTitle());
        assertEquals("New Description", response.getDescription());
    }

    @Test
    void testCreatePortfolio_TitleAlreadyExists() {
        // Given
        PortfolioCreateRequest request = new PortfolioCreateRequest();
        request.setTitle("Test Portfolio");
        request.setDescription("Test Description");
        request.setUserEmail("test@example.com");

        when(portfolioRepo.existsByTitle("Test Portfolio")).thenReturn(true);

        // When & Then
        assertThrows(CustomException.class, () -> portfolioService.create(request));
    }

    @Test
    void testUpdatePortfolio_Success() {
        // Given
        PortfolioRequest request = new PortfolioRequest();
        request.setTitle("Updated Portfolio");
        request.setDescription("Updated Description");

        when(portfolioRepo.findByTitle("Test Portfolio")).thenReturn(Optional.of(portfolio));
        when(portfolioRepo.existsByTitle("Updated Portfolio")).thenReturn(false);

        portfolio.setTitle("Updated Portfolio");
        portfolio.setDescription("Updated Description");

        when(portfolioRepo.save(any(Portfolio.class))).thenReturn(portfolio);

        PortfolioResponse expectedResponse = new PortfolioResponse();
        expectedResponse.setTitle("Updated Portfolio");
        expectedResponse.setDescription("Updated Description");

        when(modelMapper.map(portfolio, PortfolioResponse.class)).thenReturn(expectedResponse);

        // When
        PortfolioResponse response = portfolioService.updatePortfolio("Test Portfolio", request);

        // Then
        assertNotNull(response);
        assertEquals("Updated Portfolio", response.getTitle());
        assertEquals("Updated Description", response.getDescription());
    }

    @Test
    void testUpdatePortfolio_TitleAlreadyExists() {
        // Given
        PortfolioRequest request = new PortfolioRequest();
        request.setTitle("Existing Portfolio");
        request.setDescription("Updated Description");

        when(portfolioRepo.findByTitle("Test Portfolio")).thenReturn(Optional.of(portfolio));
        when(portfolioRepo.existsByTitle("Existing Portfolio")).thenReturn(true);

        // When & Then
        assertThrows(CustomException.class, () -> portfolioService.updatePortfolio("Test Portfolio", request));
    }

    @Test
    void testDeletePortfolio_Success() {
        // Given
        when(portfolioRepo.findByTitle("Test Portfolio")).thenReturn(Optional.of(portfolio));

        // When
        portfolioService.delete("Test Portfolio");

        // Then
        verify(portfolioRepo, times(1)).delete(portfolio);
    }

    @Test
    void testDeletePortfolio_NotFound() {
        // Given
        when(portfolioRepo.findByTitle("Non Existent")).thenReturn(Optional.empty());

        // When & Then
        assertThrows(CustomException.class, () -> portfolioService.delete("Non Existent"));
    }

    @Test
    void testFindAll() {
        // Given
        List<Portfolio> portfolios = List.of(portfolio);
        when(portfolioRepo.findAll()).thenReturn(portfolios);

        PortfolioResponse expectedResponse = new PortfolioResponse();
        expectedResponse.setTitle("Test Portfolio");
        expectedResponse.setDescription("Test Description");

        when(modelMapper.map(any(Portfolio.class), eq(PortfolioResponse.class))).thenReturn(expectedResponse);

        // When
        List<PortfolioResponse> responseList = portfolioService.findAll();

        // Then
        assertNotNull(responseList);
        assertEquals(1, responseList.size());
        assertEquals("Test Portfolio", responseList.get(0).getTitle());
    }
}
