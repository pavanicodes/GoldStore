package com.example.demo.model;

import lombok.Data;

@Data
public class ItemRequest {
    private String name;
    private String description;
    private String code;
    private int price;
}
