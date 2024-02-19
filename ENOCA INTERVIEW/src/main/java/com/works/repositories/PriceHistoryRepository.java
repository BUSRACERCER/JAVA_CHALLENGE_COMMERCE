package com.works.repositories;

import com.works.entities.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    List<PriceHistory> findByProductId(Long productId);

    PriceHistory findTopByProductIdOrderByCreatedAtDesc(Long productId);

}