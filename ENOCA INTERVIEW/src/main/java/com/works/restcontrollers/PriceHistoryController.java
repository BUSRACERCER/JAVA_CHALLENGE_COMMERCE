package com.works.restcontrollers;

import com.works.entities.PriceHistory;
import com.works.services.PriceHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/price-history")
@RequiredArgsConstructor
public class PriceHistoryController {


    private final PriceHistoryService priceHistoryService;

    @PostMapping("/add")
    public void addPriceHistory(@RequestParam Long productId, @RequestParam double price) {
        priceHistoryService.addPriceHistory(productId, price);
    }

    @GetMapping("/product/{productId}")
    public List<PriceHistory> getPriceHistoryForProduct(@PathVariable Long productId) {
        return priceHistoryService.getPriceHistoryForProduct(productId);
    }

    @GetMapping("/product/{productId}/current")
    public PriceHistory getCurrentPriceForProduct(@PathVariable Long productId) {
        return priceHistoryService.getCurrentPriceForProduct(productId);
    }
}