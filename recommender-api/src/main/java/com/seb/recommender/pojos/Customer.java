package com.seb.recommender.pojos;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
public class Customer {
    private int age;
    private int income;
    private boolean student;

    public Customer(int age, int income, boolean student) {
        this.age = age;
        this.income = income;
        this.student = student;
    }
}
