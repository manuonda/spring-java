package com.project.two.commons.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
public class EventoProductoDTO {
    private Long id;
    private String message;
    private String typeEvent;
    private Date cratedAt;
    private String name;
    private int qty;
    private Long idCategoria;

    public EventoProductoDTO() {

    }
}
