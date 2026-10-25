package net.balmir.ebankservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import net.balmir.ebankservice.model.Customer;

import java.util.Date;

@Entity
@AllArgsConstructor @NoArgsConstructor @Builder
@Getter @Setter
public class BankAccount {
    @Id
    private String id ;
    private Date createdAt ;
    private double balance ;
    private String type ;
    private long customerId ;
    @Transient //pas representer dans la bdd
    private Customer customer ;
}
