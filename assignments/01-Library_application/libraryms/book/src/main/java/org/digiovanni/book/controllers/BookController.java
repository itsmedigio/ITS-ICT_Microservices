package org.digiovanni.book.controllers;

import lombok.extern.slf4j.Slf4j;
import org.digiovanni.book.models.Book;
import org.digiovanni.book.repos.BookRepository;
import org.digiovanni.book.services.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(value = "/v2/book")
public class BookController {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    TraceService traceService;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Default action if we call the book API (get all books)
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Book> getAllBooks() {
        List<Book> result = new ArrayList<Book>();
        Iterable<Book> iterable = bookRepository.findAll();
        iterable.forEach(result::add);
        return result;
    }

    //Adding a book by putting with it's ID
    @RequestMapping(method = RequestMethod.PUT)
    public void addBook(@RequestBody Book book) {
        bookRepository.save(book);
        System.out.println("BOOK ADDED SUCCESSFULLY: " + book.toString());
    }

    //Get a certain book by getting it's ID
    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public Book getBook(@PathVariable String bookId) {
        Optional bookOpt = bookRepository.findById(bookId);
        if (bookOpt.isPresent()) {
            return (Book) bookOpt.get();
        } else {
            return null;
        }
    }

    //Edit the book by posting to bookId
    @RequestMapping(value = "/{bookId}", method = RequestMethod.POST)
    public Book editBook(@RequestBody Book book, @RequestBody String bookId) {
        return bookRepository.save(book);
    }

    //Remove a certain book by calling /book/bookId
    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String bookId) {
        bookRepository.deleteById(bookId);
    }

    //Remove all books by sending a delete to /book
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }

}
