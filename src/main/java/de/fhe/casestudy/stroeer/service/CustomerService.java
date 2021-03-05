package de.fhe.casestudy.stroeer.service;

import de.fhe.casestudy.stroeer.bean.BookRepository;
import de.fhe.casestudy.stroeer.bean.CustomerRepository;
import de.fhe.casestudy.stroeer.model.Book;
import de.fhe.casestudy.stroeer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Transactional
public class CustomerService {

   @Autowired
   private CustomerRepository repo;

   public List<Customer> listAll() {
      return repo.findAll();
   }

   public void save(Customer c) {
      repo.save(c);
   }

   public Customer get(long id) {
      return repo.findById(id).orElse(null);
   }

   public Customer getByEmail(String email) {
      List<Customer> byEmail = repo.findByEmail(email);
      return CollectionUtils.isEmpty(byEmail) ? null : byEmail.get(0);
   }

   public void delete(long id) {
      repo.deleteById(id);
   }
}
