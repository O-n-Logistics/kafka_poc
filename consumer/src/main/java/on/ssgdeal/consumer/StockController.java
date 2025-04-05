package on.ssgdeal.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import on.ssgdeal.consumer.dto.CreateStockDto;
import on.ssgdeal.consumer.dto.DecreaseStockDto;
import on.ssgdeal.consumer.dto.IncreaseStockDto;
import on.ssgdeal.consumer.request.CreateStockRequest;
import on.ssgdeal.consumer.request.DecreaseStockRequest;
import on.ssgdeal.consumer.request.IncreaseStockRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks")
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

        return "get stock success: " + stock.getStock();
    }

    @PatchMapping("/{id}/decrease")
    public String decreaseStock(
        @PathVariable Long id,
        DecreaseStockRequest request
    ) {
        log.info("decreaseStock, {}, {}", id, request);
        DecreaseStockDto dto = DecreaseStockDto.from(id, request);
//        stockService.decreaseStock(dto.stockId(), dto.decreaseAmount());

        return "decrease stocks success";
    }

    @PatchMapping("/{id}/increase")
    public String increaseStock(
        @PathVariable Long id,
        IncreaseStockRequest request
    ) {
        log.info("increaseStock, {}, {}", id, request);
        IncreaseStockDto dto = IncreaseStockDto.from(id, request);
        //stockService.increaseStock(dto.stockId(), dto.increaseAmount());

        return "increase stock success";
    }

}
