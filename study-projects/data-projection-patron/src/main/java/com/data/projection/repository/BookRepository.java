package com.data.projection.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.projection.dto.BookDTO;
import com.data.projection.entity.Book;
import com.data.projection.projections.PersonLocation;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	// Select * from book where name=?1
	List<BookDTO> findByName(String name);

	Optional<Book> findById(Long idProducto);
	

	
}
