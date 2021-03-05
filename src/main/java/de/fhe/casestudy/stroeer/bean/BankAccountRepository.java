package de.fhe.casestudy.stroeer.bean;

import de.fhe.casestudy.stroeer.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
   List<BankAccount> findByCustomerId(Long customerId);
}
