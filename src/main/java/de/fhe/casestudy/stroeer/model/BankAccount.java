package de.fhe.casestudy.stroeer.model;


import javax.persistence.*;

@Entity
@Table(name = "bankAccount")
public class BankAccount {
   private Long id;
   private Long customerId;
   private String iban;

   public BankAccount() {
   }

   public BankAccount(Long id, Long customerId, String iban) {
      this.id = id;
      this.customerId = customerId;
      this.iban = iban;
   }
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Long getCustomerId() {
      return customerId;
   }

   public void setCustomerId(Long customerId) {
      this.customerId = customerId;
   }

   public String getIban() {
      return iban;
   }

   public void setIban(String iban) {
      this.iban = iban;
   }
}
