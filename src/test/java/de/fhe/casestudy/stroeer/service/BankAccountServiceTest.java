package de.fhe.casestudy.stroeer.service;

import de.fhe.casestudy.stroeer.bean.BankAccountRepository;
import de.fhe.casestudy.stroeer.bean.CustomerRepository;
import de.fhe.casestudy.stroeer.model.BankAccount;
import de.fhe.casestudy.stroeer.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankAccountServiceTest {

   @Autowired
   private BankAccountRepository repo;

   @Test
   void getBankAccountsByCustomerId() {
      assertThat(repo).isNotNull();
      List<BankAccount> c = repo.findByCustomerId(1L);
      assertThat(c).isNotEmpty();
      c = repo.findByCustomerId(-1L);
      assertThat(c).isEmpty();
   }
}