package com.example.dto;

import java.util.Date;

public record ErrorDTO(String message, Integer statusCode, Date timestamp){
}
