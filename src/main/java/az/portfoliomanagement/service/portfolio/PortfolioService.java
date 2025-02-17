package az.portfoliomanagement.service.portfolio;

import az.portfoliomanagement.dto.request.PortfolioCreateRequest;
import az.portfoliomanagement.dto.request.PortfolioRequest;
import az.portfoliomanagement.dto.response.PortfolioResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PortfolioService {

    PortfolioResponse findByTitle(String title);

    PortfolioResponse create(PortfolioCreateRequest portfolioCreateRequest);

    PortfolioResponse updatePortfolio(String title, PortfolioRequest portfolioRequest);

    void delete(String title);

    List<PortfolioResponse> findAll();
}
