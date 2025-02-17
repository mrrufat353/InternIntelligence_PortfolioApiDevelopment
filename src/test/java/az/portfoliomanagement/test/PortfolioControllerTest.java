package az.portfoliomanagement.test;

import az.portfoliomanagement.controller.PortfolioController;
import az.portfoliomanagement.dto.request.PortfolioCreateRequest;
import az.portfoliomanagement.dto.request.PortfolioRequest;
import az.portfoliomanagement.dto.response.PortfolioResponse;
import az.portfoliomanagement.service.portfolio.PortfolioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PortfolioController.class)
public class PortfolioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PortfolioService portfolioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void findByTitle_ReturnsPortfolio() throws Exception {
        PortfolioResponse response = new PortfolioResponse(1L, "My Portfolio", "Description");
        Mockito.when(portfolioService.findByTitle("My Portfolio")).thenReturn(response);

        mockMvc.perform(get("/api/portfolios/find/My Portfolio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("My Portfolio"));
    }

    @Test
    void findAll_ReturnsListOfPortfolios() throws Exception {
        List<PortfolioResponse> responses = Arrays.asList(
                new PortfolioResponse(1L, "Portfolio1", "Desc1"),
                new PortfolioResponse(2L, "Portfolio2", "Desc2")
        );
        Mockito.when(portfolioService.findAll()).thenReturn(responses);

        mockMvc.perform(get("/api/portfolios/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Portfolio1"));
    }

    @Test
    void createPortfolio_ReturnsCreatedPortfolio() throws Exception {
        PortfolioCreateRequest request = new PortfolioCreateRequest("New Portfolio", "Desc", "user@example.com", null, null, null, null);
        PortfolioResponse response = new PortfolioResponse(1L, "New Portfolio", "Desc");
        Mockito.when(portfolioService.create(any(PortfolioCreateRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/portfolios/create")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Portfolio"));
    }

    @Test
    void updatePortfolio_ReturnsUpdatedPortfolio() throws Exception {
        PortfolioRequest request = new PortfolioRequest("Updated Portfolio", "Updated Desc");
        PortfolioResponse response = new PortfolioResponse(1L, "Updated Portfolio", "Updated Desc");
        Mockito.when(portfolioService.updatePortfolio(anyString(), any(PortfolioRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/portfolios/update/My Portfolio")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Portfolio"));
    }

    @Test
    void deletePortfolio_ReturnsNoContent() throws Exception {
        Mockito.doNothing().when(portfolioService).delete(anyString());

        mockMvc.perform(delete("/api/portfolios/delete/My Portfolio"))
                .andExpect(status().isOk());
    }
}

