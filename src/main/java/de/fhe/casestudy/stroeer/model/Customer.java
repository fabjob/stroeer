package de.fhe.casestudy.stroeer.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer {
   private Long id;
   private String email;
   private String firstName;
   private String lastName;

   public Customer() {
   }

   public Customer(Long id, String email, String firstName, String lastName) {
      this.id = id;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
}