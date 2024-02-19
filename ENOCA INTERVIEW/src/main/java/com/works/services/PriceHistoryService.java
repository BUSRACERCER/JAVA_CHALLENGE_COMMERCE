package com.works.services;

import com.works.entities.PriceHistory;
import com.works.entities.Product;
import com.works.repositories.PriceHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;
    private final ProductService productService;

    public void addPriceHistory(Long productId, double price) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            PriceHistory priceHistory = new PriceHistory();
            priceHistory.setProduct(product);
            priceHistory.setPrice(price);
            priceHistoryRepository.save(priceHistory);
        } else {
            throw new IllegalArgumentException("Product with id " + productId + " not found.");
        }
    }

    public List<PriceHistory> getPriceHistoryForProduct(Long productId) {
        return priceHistoryRepository.findByProductId(productId);
    }

    public PriceHistory getCurrentPriceForProduct(Long productId) {
        return priceHistoryRepository.findTopByProductIdOrderByCreatedAtDesc(productId);
    }
}
