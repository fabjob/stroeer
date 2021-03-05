package de.fhe.casestudy.stroeer.service;

import de.fhe.casestudy.stroeer.bean.BankAccountRepository;
import de.fhe.casestudy.stroeer.bean.CustomerRepository;
import de.fhe.casestudy.stroeer.model.BankAccount;
import de.fhe.casestudy.stroeer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional
public class BankAccountService {

   @Autowired
   private BankAccountRepository repo;

   public List<BankAccount> listAll() {
      return repo.findAll();
   }

   public void save(BankAccount c) {
      repo.save(c);
   }

   public BankAccount get(long id) {
      return repo.findById(id).orElse(null);
   }

   public List<BankAccount> getByCustomerId(Long customerId) {
      return repo.findByCustomerId(customerId);
   }

   public void delete(long id) {
      repo.deleteById(id);
   }
}
