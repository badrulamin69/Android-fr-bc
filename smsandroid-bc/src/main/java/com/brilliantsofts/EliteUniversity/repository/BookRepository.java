package com.brilliantsofts.EliteUniversity.repository;

import com.brilliantsofts.EliteUniversity.entity.Book;

import java.util.List;

public interface BookRepository extends org.springframework.data.jpa.repository.JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
