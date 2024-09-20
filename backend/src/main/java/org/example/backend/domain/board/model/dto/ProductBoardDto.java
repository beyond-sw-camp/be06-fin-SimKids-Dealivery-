package org.example.backend.domain.board.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.example.backend.domain.board.category.model.entity.Category;
import org.example.backend.domain.board.model.entity.ProductBoard;
import org.example.backend.domain.board.model.entity.ProductThumbnailImage;
import org.example.backend.domain.board.product.model.dto.ProductDto;
import org.example.backend.global.common.constants.BoardStatus;
import org.example.backend.global.common.constants.CategoryType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductBoardDto {

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BoardCreateRequest{
		private String title;
		private Integer discountRate;
		private List<ProductDto.Request> products;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private CategoryType category;

		public ProductBoard toEntity(String thumbnailUrl, String detailUrl, Category category) {
			Integer minimumPrice = this.products.stream()
				.map(ProductDto.Request::getPrice)
				.min(Integer::compareTo)
				.orElse(Integer.MAX_VALUE);

			return ProductBoard.builder()
				.title(this.title)
				.discountRate(this.discountRate)
				.startedAt(this.startedAt)
				.endedAt(this.endedAt)
				.category(category)
				.productThumbnailUrl(thumbnailUrl)
				.productDetailUrl(detailUrl)
				.status(BoardStatus.READY.getStatus())
				.minimumPrice(minimumPrice)
				.build();
		}
		public ProductThumbnailImage toEntity(String thumbnailUrl, ProductBoard productBoard) {
			return ProductThumbnailImage.builder()
				.productBoard(productBoard)
				.productThumbnailUrl(thumbnailUrl)
				.build();
		}
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BoardListResponse {
		private Long idx;
		private String productThumbnailUrl;
		private String title;
		private String companyName;
		private String status;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private Boolean likes;
		private Integer price;
		private Integer discountRate;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class BoardDetailResponse {
		private List<String> productThumbnailUrls;
		private String productDetailUrl;
		private String title;
		private List<ProductDto.Response> products;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private String companyName;
		private String category;
		private Boolean likes;
		private Integer price;
		private Integer discountRate;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CompanyBoardListResponse {
		private Long idx;
		private String productThumbnailUrl;
		private String title;
		private String category;
		private String status;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CompanyBoardDetailResponse {
		private List<String> productThumbnailUrls;
		private String productDetailUrl;
		private String title;
		private Integer discountRate;
		private List<ProductDto.CompanyResponse> products;
		private LocalDateTime startedAt;
		private LocalDateTime endedAt;
		private String category;
	}
}
