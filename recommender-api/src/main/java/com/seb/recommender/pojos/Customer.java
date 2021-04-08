package com.seb.recommender.pojos;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class Customer {
    private int age;
    private int income;
    private boolean student;
}
