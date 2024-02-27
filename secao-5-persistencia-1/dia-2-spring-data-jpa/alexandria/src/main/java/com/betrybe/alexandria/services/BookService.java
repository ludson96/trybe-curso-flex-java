package com.betrybe.alexandria.services;

import com.betrybe.alexandria.models.entities.Book;
import com.betrybe.alexandria.models.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public Book insertBook(Book book) {
    return bookRepository.save(book);
  }
}