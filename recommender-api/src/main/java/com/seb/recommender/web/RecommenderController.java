package com.seb.recommender.web;

import com.seb.recommender.pojos.Customer;
import com.seb.recommender.pojos.Product;
import com.seb.recommender.services.RecommenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
public class RecommenderController {
    private final RecommenderService recommenderService;

    @Autowired
    public RecommenderController(RecommenderService recommenderService)
    {
        this.recommenderService = recommenderService;
    }

    @GetMapping(path = "/products", produces = "application/json")
    public ResponseEntity<List<Product>> getCustomerAnswer(Customer customer) {
        return ok(this.recommenderService.getProducts(customer));
    }
}
