package com.test.task.controller;

import com.test.task.model.Book;
import com.test.task.model.BookDao;
import com.test.task.model.BookQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;

    @PostMapping("/books/add")
    public Book create(@RequestBody Book book) {
        return bookDao.save(book);
    }

    @GetMapping("/books/view/all-with-sort")
    public List<Book> viewAllSort() {
        return bookDao.findAllSortReverseTitle();
    }

    @GetMapping("/books/view/all-with-group")
    public List<BookQuery> viewAllGroup() {
        return bookDao.findAllGroupAuthor();
    }

}
