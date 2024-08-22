package com.example.demo.model;

import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private String name;
    private String description;
    private String code;
    private int price;
    private ReturnPolicyDTO returnPolicyDTO;
}
