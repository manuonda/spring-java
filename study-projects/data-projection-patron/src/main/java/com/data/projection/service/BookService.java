package com.data.projection.service;

import org.springframework.stereotype.Service;

import com.data.projection.repository.BookRepository;

@Service
public class BookService  {

	private BookRepository bookRepository;
	
	public BookService( BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
}
