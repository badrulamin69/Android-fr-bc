package com.brilliantsofts.EliteUniversity.dto.response;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String title;
    private String isbn;
    private String author;
    private Integer quantity;
    private Integer availableQuantity;
}
