package com.services.base.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderEvent {
  private String message;
  private String status;
  private OrderDTO order;

  @Override
  public String toString() {
    return "OrderEvent{" +
            "message='" + message + '\'' +
            ", status='" + status + '\'' +
            ", order=" + order +
            '}';
  }
}
