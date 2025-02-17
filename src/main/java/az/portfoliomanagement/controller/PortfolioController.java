package az.portfoliomanagement.controller;

import az.portfoliomanagement.dto.request.PortfolioCreateRequest;
import az.portfoliomanagement.dto.request.PortfolioRequest;
import az.portfoliomanagement.dto.response.PortfolioResponse;
import az.portfoliomanagement.service.portfolio.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping("/find/{title}")
    public ResponseEntity<PortfolioResponse> findByTitle(@PathVariable String title) {
        return new ResponseEntity<>(portfolioService.findByTitle(title), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PortfolioResponse>> findAll(){
        return new ResponseEntity<>(portfolioService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PortfolioResponse> createPortfolio(@Valid @RequestBody PortfolioCreateRequest portfolioCreateRequest) {
        return new ResponseEntity<>(portfolioService.create(portfolioCreateRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{title}")
    public ResponseEntity<PortfolioResponse> updatePortfolio(@PathVariable String title,
                                                             @Valid @RequestBody PortfolioRequest portfolioRequest) {
        return new ResponseEntity<>(portfolioService.updatePortfolio(title, portfolioRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{title}")
    public void delete(@PathVariable String title) {
        portfolioService.delete(title);
    }
}
