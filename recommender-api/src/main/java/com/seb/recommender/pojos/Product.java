package com.seb.recommender.pojos;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class Product {
    private String type;

    public Product(String type) {
        this.type = type;
    }
}
