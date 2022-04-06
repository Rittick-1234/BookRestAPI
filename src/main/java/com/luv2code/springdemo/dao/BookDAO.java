package com.luv2code.springdemo.dao;

import java.util.List;

import com.luv2code.springdemo.entity.Book;

public interface BookDAO {

	public List<Book> getBooks();

	public void saveBook(Book theBook);

	public Book getBook(int theCode);

	public void deleteBook(int theCode);
	
}
