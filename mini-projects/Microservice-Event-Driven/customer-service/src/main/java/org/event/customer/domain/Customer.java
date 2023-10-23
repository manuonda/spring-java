package org.event.customer.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="tbl_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idType;
    private String idValue;
    private String firstName;
    private String lastName;
    private String gender;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private LocalDateTime created;

}
