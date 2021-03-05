package de.fhe.casestudy.stroeer.service;

import de.fhe.casestudy.stroeer.bean.CustomerRepository;
import de.fhe.casestudy.stroeer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CustomerServiceTest {

   @Autowired
   private CustomerRepository repo;

   @Test
   void getCustomerByEmail() {
      assertThat(repo).isNotNull();
      List<Customer> c = repo.findByEmail("fheidemeyer@web.de");
      assertThat(c).isNotEmpty();
      c = repo.findByEmail("invalid");
      assertThat(c).isEmpty();
   }
}