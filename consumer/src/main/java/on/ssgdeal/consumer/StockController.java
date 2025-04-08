package on.ssgdeal.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
@Slf4j(topic = "StockController")
public class StockController {

    private final StockService stockService;

    @PostMapping
    public String createStock(
        @RequestBody CreateStockRequest request
    ) {
        log.info("createStock, {}", request);
        CreateStockDto dto = CreateStockDto.from(request);
        Long createdStockId = stockService.createStock(dto);
        return "create stock success: " + createdStockId;
    }

    @GetMapping("/{id}")
    public String getStock(
        @PathVariable Long id
    ) {
        log.info("getStock, {}", id);
        Stock stock = stockService.findStock(id);
        return "get stock success... " + stock.getProductName() + ": " + stock.getStock();
    }
}
