package com.example.kafka.kafkatutorial1.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerVisitEvent {
    
  private String customerId;

  private String message;

  

}
