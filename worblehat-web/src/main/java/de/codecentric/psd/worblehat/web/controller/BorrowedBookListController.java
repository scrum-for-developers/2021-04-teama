package de.codecentric.psd.worblehat.web.controller;

import de.codecentric.psd.worblehat.domain.Book;
import de.codecentric.psd.worblehat.domain.BookService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Controller class for the book table result. */
@Controller
@RequestMapping("/borrowedBooks")
public class BorrowedBookListController {

  private BookService bookService;

  @Autowired
  public BorrowedBookListController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public String setupForm(ModelMap modelMap) {
    List<Book> books = bookService.findAllBooks();
    Stream<Book> filteredBooksStream = books.stream().filter(book -> book.getBorrowing() != null);
    List<Book> filteredBooks = filteredBooksStream.collect(Collectors.toList());
    modelMap.addAttribute("books", filteredBooks);
    return "bookList";
  }
}
