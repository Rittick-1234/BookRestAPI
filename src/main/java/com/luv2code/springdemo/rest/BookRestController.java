package com.luv2code.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Book;
import com.luv2code.springdemo.service.BookService;

@RestController
@RequestMapping("/api")
public class BookRestController {

	//autowire the BookService
	@Autowired
	private BookService bookService;
	
	//add mapping for GET/customers
	@GetMapping("/books")
	public List<Book> getBooks(){
		
		return bookService.getBooks();
	}
	
	
	//add mapping for GET/customers/{customerID}
	@GetMapping("/books/{bookId}")
	public  Book getBook(@PathVariable int bookId) {
		
		Book theBook=bookService.getBook(bookId);
		
		if(theBook==null) {
			throw new BookNotFoundException("Book Code not found - "+bookId);
		}
		return theBook;
	}
	
	//add mapping for POST/customers - add new Customer
	@PostMapping("/books")
	public Book addBook(@RequestBody Book theBook) {
		
		// also just in case they pass an id in JSON--set id to 0
		//this a force a save new item instead of update
		
		theBook.setBookCode(0);
		
		bookService.saveBook(theBook);
		
		return theBook;
	}
	
	
	//add mapping for PUT/customers -- update existing customers
	
	@PutMapping("/books")
	public Book updateBook(@RequestBody Book theBook) {
		
		bookService.saveBook(theBook);
		
		return theBook;
	}
	
	//add mapping for DELETE/customers/{customerId} - delete customer
	
	@DeleteMapping("/books/{bookId}")
	public String deleteCustomer(@PathVariable int bookId) {
		
		Book tempBook=bookService.getBook(bookId);
		
		//throw exception if null
		if(tempBook==null) {
			throw new BookNotFoundException("Book Code not found - "+bookId);
		}
		
		bookService.deleteBook(bookId);
		
		return "Deleted Book Code - " + bookId;
	}
}
