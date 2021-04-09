package com.seb.recommender.unit.services;

import com.seb.recommender.pojos.Customer;
import com.seb.recommender.pojos.Product;
import org.drools.core.reteoo.ReteDumper;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.conf.PropertySpecificOption;
import org.kie.internal.utils.KieHelper;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class RecommenderServiceTest {
    private String drl;
    private KieSession kieSession;

    @Before
    public void setUp() {
        drl = "import " + Customer.class.getCanonicalName() + ";\n" +
                "import " + Product.class.getCanonicalName() + ";\n" +
                "global java.util.List products;\n" +
                "rule \"young rich\" when\n" +
                "    $customer : Customer( age == 17 && income > 50000) \n" +
                "then\n" +
                "    products.add(new Product(\"young rich\"));\n" +
                "end\n" +
                "rule \"old poor\" when\n" +
                "    $customer : Customer( age > 17 && income < 200) \n" +
                "then\n" +
                "    products.add(new Product(\"old poor\"));\n" +
                "end\n";

        kieSession = new KieHelper(PropertySpecificOption.ALWAYS)
                .addContent(drl, ResourceType.DRL)
                .build()
                .newKieSession();
    }

    @Test
    public void testGetProducts() {
        System.out.println(drl);

        ReteDumper.dumpRete(kieSession);
        List<Product> products = new ArrayList<>();
        kieSession.setGlobal("products", products);

        Customer mario = new Customer(18, 100, false);
        Customer elena = new Customer(17, 90000, true);
        Customer marit = new Customer(20, 20000, false);

        kieSession.insert(mario);
        kieSession.insert(elena);
        kieSession.insert(marit);

        kieSession.fireAllRules();

        assertEquals(2, products.size());
        assertEquals(new Product("young rich"), products.get(0));
        assertEquals(new Product("old poor"), products.get(1));
    }
}
