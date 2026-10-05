package net.balmir.customersevice.service;

import net.balmir.customersevice.entities.Customer;
import net.balmir.customersevice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    // Chercher un customer by Id
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }
    //Ajouter un customer
    public Customer saveCustomer (Customer customer) {
        return customerRepository.save(customer);
    }

    //


}
