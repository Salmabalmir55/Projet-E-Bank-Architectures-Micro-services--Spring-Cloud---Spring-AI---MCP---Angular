package net.balmir.ebankservice.model;


import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {
    private Long id ;
    private String name;
    private String email;

}
