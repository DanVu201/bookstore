package com.bookstore.adminportal.controller;

import com.bookstore.adminportal.domain.Book;
import com.bookstore.adminportal.domain.CouponImport;
import com.bookstore.adminportal.service.BookService;
import com.bookstore.adminportal.service.CouponImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CouponImportService couponImportService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBook";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) throws IOException {
        MultipartFile bookImage = book.getBookImage();
        if (!bookImage.isEmpty()) {
            book.setImageShow(bookImage.getBytes());
        } else {
            book.setImageShow(new byte[0]);
        }
        book.setInStockNumber(0);
        bookService.save(book);

        return "redirect:bookList";
    }

    @RequestMapping("/bookInfo")
    public String bookInfo(@RequestParam("id") Long id, Model model) {
        Book book = bookService.findById(id);
        String image;
        try {
            image = Base64.getEncoder().encodeToString(book.getImageShow());
        } catch (Exception ignored) {
            image = "";
        }
        book.setImageString(image);
        model.addAttribute("book", book);
        return "bookInfo";
    }


    @RequestMapping("/updateBook")
    public String updateBook(@RequestParam("id") Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "updateBook";
    }


    @RequestMapping(value = "/updateBook", method = RequestMethod.POST)
    public String updateBookPost(@ModelAttribute("book") Book book, HttpServletRequest request) throws IOException {
        MultipartFile bookImage = book.getBookImage();
        if (!bookImage.isEmpty()) {
            book.setImageShow(bookImage.getBytes());
        } else {
            book.setImageShow(bookService.findById(book.getId()).getImageShow());
        }
        bookService.save(book);
        return "redirect:/book/bookInfo?id=" + book.getId();
    }

    @RequestMapping("/bookList")
    public String bookList(Model model) {
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);
        return "bookList";

    }

    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String remove(@RequestParam(value = "id") String id, Model model) {
        bookService.deleteById(Long.parseLong(id));
        List<Book> bookList = bookService.findAll();
        model.addAttribute("bookList", bookList);

        return "redirect:/book/bookList";
    }

    @RequestMapping(value = "/import", method = RequestMethod.GET)
    public String importBook(@RequestParam(value = "id") Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "importBook";
    }

    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public String importBook(@ModelAttribute("book") Book book, HttpServletRequest request) {
        Book book1 = bookService.findById(book.getId());
        book1.setInStockNumber(book1.getInStockNumber() + book.getInStockNumber());
        bookService.save(book1);
        CouponImport couponImport = CouponImport.builder()
                .dateImport(LocalDate.now())
                .numberImport(book.getInStockNumber())
                .priceBook(book.getPrice())
                .totalPrice(book.getPrice() * book.getInStockNumber())
                .book(book1)
                .build();
        couponImportService.save(couponImport);
        return "redirect:/book/bookInfo?id=" + book.getId();
    }
}
