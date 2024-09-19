package org.example.backend.domain.board.product.model.entity;

import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.product.model.dto.ProductDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	private String name;
	private Integer stock;
	private Integer price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="product_board_idx")
	private ProductBoard productBoard;

    public void decreaseStock(Integer quantity) {
		this.stock -= quantity;
    }

	public ProductDto.Request toDto() {
		return ProductDto.Request.builder()
			.name(this.name)
			.stock(this.stock)
			.price(this.price)
			.build();
	}
}