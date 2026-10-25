package net.balmir.customerservice.service;

import net.balmir.customerservice.entities.Customer;
import net.balmir.customerservice.repository.CustomerRepository;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
    }
    //Ajouter un customer
    @McpTool(description="Save a new Customer")
    public Customer saveCustomer (@McpToolParam(description = "The customer to save (name , email) ") Customer customer) {
        return customerRepository.save(customer);
    }

    //
}
