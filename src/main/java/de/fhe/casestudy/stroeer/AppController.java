package de.fhe.casestudy.stroeer;

import de.fhe.casestudy.stroeer.model.BankAccount;
import de.fhe.casestudy.stroeer.model.Customer;
import de.fhe.casestudy.stroeer.service.BankAccountService;
import de.fhe.casestudy.stroeer.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AppController {
   private static final Logger LOG = LogManager.getLogger();

   @Autowired
   private CustomerService customerService;
   @Autowired
   private BankAccountService bankAccountService;

   @RequestMapping("/")
   public String redirect(Model model) {
      return "redirect:/login/";
   }

   @RequestMapping("/login/")
   public String viewLoginPage(Model model) {
      Customer c = new Customer();
      model.addAttribute("customer", c);
      return "login";
   }

   @RequestMapping(value = "/customer/login", method = RequestMethod.POST)
   public String loginSubmittedPage(Model model, @ModelAttribute("customer") Customer c) {
      Customer existing = customerService.getByEmail(c.getEmail());
//      List<Product> listProducts = service.listAll();
//      model.addAttribute("listProducts", listProducts);
      if (existing == null)
         return "login";  //  TODO:FH  create new customer

      model.addAttribute("customer", existing);
      List<BankAccount> bankAccounts = bankAccountService.getByCustomerId(existing.getId());
      model.addAttribute("bankAccounts", bankAccounts);

      return "customer_show";
   }

   @RequestMapping("/bankaccount/delete/{id}")
   public String deleteBankAccount(Model model, @PathVariable(name = "id") int id) {
      BankAccount bankAccount = bankAccountService.get(id);
      bankAccountService.delete(id);

      List<BankAccount> bankAccounts = bankAccountService.getByCustomerId(bankAccount.getCustomerId());
      model.addAttribute("bankAccounts", bankAccounts);
      model.addAttribute("customer", customerService.get(bankAccount.getCustomerId()));

      return "customer_show";
   }

   @RequestMapping(value = "/bankaccount/new/", method = RequestMethod.POST)
   public String showAddBankAccountPage(Model model/*
         , @ModelAttribute("bankaccount2") BankAccount ba*/) {
//      ba.setCustomerId((long) customerId);
//      model.addAttribute("bankaccount", ba);

      return "bankAccount_new";
   }
}
