package com.fliway;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookDepository extends CrudRepository<Book, String> {

}
