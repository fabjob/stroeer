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
   public String showLoginPage(Model model) {
      Customer c = new Customer();
      model.addAttribute("customer", c);
      return "login";
   }

   @RequestMapping(value = "/customer/login", method = RequestMethod.POST)
   public String loginSubmitted(Model model, @ModelAttribute("customer") Customer c) {
      Customer existing = customerService.getByEmail(c.getEmail());
      if (existing == null) {
         //  create new customer
         model.addAttribute("customer", c);
         return "customer_new";
      }

      model.addAttribute("customer", existing);
      List<BankAccount> bankAccounts = bankAccountService.getByCustomerId(existing.getId());
      model.addAttribute("bankAccounts", bankAccounts);

      model.addAttribute("newAccount", new BankAccount());

      return "customer_show";
   }

   @RequestMapping(value = "/customer/new", method = RequestMethod.POST)
   public String newCustomer(Model model, @ModelAttribute("customer") Customer c) {
      customerService.save(c);

      model.addAttribute("customer", c);
      List<BankAccount> bankAccounts = bankAccountService.getByCustomerId(c.getId());
      model.addAttribute("bankAccounts", bankAccounts);

      model.addAttribute("newAccount", new BankAccount());

      return "customer_show";
   }

   @RequestMapping(value = "/bankaccount/new/{customerId}", method = RequestMethod.POST)
   public String showAddBankAccountPage(Model model, @PathVariable(name = "customerId") int customerId
         , @ModelAttribute("newAccount") BankAccount newAccount) {


      Customer c = customerService.get(customerId);
      model.addAttribute("customer", c);
      bankAccountService.save(newAccount);  //  TODO:FH validate
      List<BankAccount> bankAccounts = bankAccountService.getByCustomerId(c.getId());   //  TODO:FH read with customer
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
      model.addAttribute("newAccount", new BankAccount());

      return "customer_show";
   }
}
