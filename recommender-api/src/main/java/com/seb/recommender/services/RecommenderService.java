package com.seb.recommender.services;

import com.seb.recommender.pojos.Customer;
import com.seb.recommender.pojos.Product;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RecommenderService {
    private final KieContainer kieContainer;

    @Autowired
    public RecommenderService(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public List<Product> getProducts(Customer customer) {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        try {
            kieSession.insert(customer);
            kieSession.setGlobal("products", new ArrayList<>());
            kieSession.fireAllRules();
        } catch (Throwable exception) {
            log.error(exception.getMessage());
        } finally {
            kieSession.dispose();
        }

        @SuppressWarnings("unchecked")
        ArrayList<Product> productList = (ArrayList<Product>) kieSession.getGlobals().get("products");

        return productList;
    }
}
