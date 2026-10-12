package net.balmir.customersevice.service;

import net.balmir.customersevice.entities.Customer;
import net.balmir.customersevice.repository.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @McpTool(description="Get All Customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    // Chercher un customer by Id
    @McpTool(description="Find Customer by ID")
    public Customer findById(@McpToolParam(description = "The ID of the customer to find") Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }
    //Ajouter un customer
    @McpTool(description="Save a new Customer")
    public Customer saveCustomer (@McpToolParam(description = "The customer to save (name , email) ") Customer customer) {
        return customerRepository.save(customer);
    }

    //


}
