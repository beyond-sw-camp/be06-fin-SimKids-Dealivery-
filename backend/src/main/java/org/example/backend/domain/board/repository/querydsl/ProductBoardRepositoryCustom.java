package org.example.backend.domain.board.repository.querydsl;

import org.example.backend.domain.board.model.entity.ProductBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductBoardRepositoryCustom {
	Page<ProductBoard> search(String status, Integer month, Pageable pageable);
}