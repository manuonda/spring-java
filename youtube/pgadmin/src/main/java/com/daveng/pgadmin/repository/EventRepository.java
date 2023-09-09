package com.daveng.pgadmin.repository;

import org.springframework.data.repository.ListCrudRepository;

import com.daveng.pgadmin.event.Event;

public interface EventRepository extends ListCrudRepository<Event, Long>{

}
