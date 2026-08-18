package com.brilliantsofts.EliteUniversity.dto.request;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String isbn;
    private String author;
    private Integer quantity;
    private Integer availableQuantity;
}
