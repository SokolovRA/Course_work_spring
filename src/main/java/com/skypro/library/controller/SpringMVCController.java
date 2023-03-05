package com.skypro.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.skypro.library.service.BookService;

@Controller
@RequestMapping("/skypro")
public class SpringMVCController {
private BookService bookService;

    public SpringMVCController(BookService bookService) {
        this.bookService = bookService;
    }
    @RequestMapping("/web")
    public String getBooks(Model model){
        model.addAttribute("books",bookService.getBooks());
        return "dashboard";
    }
}
