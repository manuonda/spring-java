package com.data.projection.projections;

import org.springframework.beans.factory.annotation.Value;

// Open Projection Full
public interface PersonFullLocation {
  @Value("#{target.firstName + ' ' + target.lastName + ' ' + target.phoneNumber + ' ' +target.street}")
  String getFullLocation();
}
