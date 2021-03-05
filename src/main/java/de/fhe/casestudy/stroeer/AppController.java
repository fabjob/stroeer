package de.fhe.casestudy.stroeer;

import java.util.List;

import de.fhe.casestudy.stroeer.model.BankAccount;
import de.fhe.casestudy.stroeer.model.Book;
import de.fhe.casestudy.stroeer.model.Customer;
import de.fhe.casestudy.stroeer.model.Product;
import de.fhe.casestudy.stroeer.service.BankAccountService;
import de.fhe.casestudy.stroeer.service.BookService;
import de.fhe.casestudy.stroeer.service.CustomerService;
import de.fhe.casestudy.stroeer.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
   private static final Logger LOG = LogManager.getLogger();

   @Autowired
   private BookService bookService;
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

   @RequestMapping("/bankaccount/new/{customerId}")
   public String showAddBankAccountPage(Model model, @PathVariable(name = "customerId") int customerId) {
      BankAccount ba = new BankAccount();
      ba.setCustomerId((long) customerId);
      model.addAttribute("bankAccount", ba);

      return "bankAccount_new";
   }
/*



   @RequestMapping("/customer/account")
   public String viewBankAccount(Model model, @ModelAttribute("customer") Customer c) {
      List<Book> listBooks = bookService.listAll();
      model.addAttribute("listBooks", listBooks);

      return "index_book";
   }
*/




////////////////////////////////
   @RequestMapping("/version")
   public String version(Model model) {
      Package pkg = AppController.class.getPackage();
      String product = null;
      String version = null;
      try {
         product = pkg.getImplementationVendor();
         version = pkg.getImplementationVersion();
      } catch (Exception e) {
         LOG.warn("unable to read manifest information");
      }

      return new StringBuilder().append(product).append(": ").append(version).toString();
   }


   @RequestMapping("/books/")
   public String viewBooks(Model model) {
      List<Book> listBooks = bookService.listAll();
      model.addAttribute("listBooks", listBooks);

      return "index_book";
   }

   @RequestMapping("/books/edit/{id}")
   public ModelAndView showEditBook(@PathVariable(name = "id") int id) {
      ModelAndView mav = new ModelAndView("edit_book");
      Book book = bookService.get(id);
      mav.addObject("book", book);

      return mav;
   }

   @RequestMapping("/books/new")
   public String showNewBookPage(Model model) {
      Book b = new Book();
      model.addAttribute("book", b);

      return "new_book";
   }

   @RequestMapping(value = "/books/save", method = RequestMethod.POST)
   public String saveBook(@ModelAttribute("book") Book b) {
      bookService.save(b);

      return "redirect:/books/";
   }
}
