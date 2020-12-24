package com.example.demoh2.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demoh2.entity.Task;

/**
 * Interfaz TaskDAO
 * @author dgarcia
 * @version 1.0
 * 
 *
 */
public interface ITaskDAO extends CrudRepository<Task, Long> {

}
