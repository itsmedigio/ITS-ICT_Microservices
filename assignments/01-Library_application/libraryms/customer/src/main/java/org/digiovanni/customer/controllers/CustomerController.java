package org.digiovanni.customer.controllers;

import lombok.extern.slf4j.Slf4j;
import org.digiovanni.customer.models.Customer;
import org.digiovanni.customer.repos.CustomerRepository;
import org.digiovanni.customer.services.TraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/v2/customer")
public class CustomerController {

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    TraceService traceService;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Default action if we call the customer API (get all customers)
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Customer> getAllCustomers() {
        List<Customer> result = new ArrayList<Customer>();
        Iterable<Customer> iterable = customerRepository.findAll();
        iterable.forEach(result::add);
        return result;
    }

    //Adding a customer by putting with it's ID
    @RequestMapping(method = RequestMethod.PUT)
    public void addCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        System.out.println("CUSTOMER ADDED SUCCESSFULLY: " + customer.getCustomerName());
    }

    //Get a certain customer by getting it's ID
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable String customerId) {
        Optional customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            return (Customer) customerOpt.get();
        } else {
            return null;
        }
    }

    //Edit the customer by posting to customerId
    @RequestMapping(value = "/{customerId}", method = RequestMethod.POST)
    public Customer editCustomer(@RequestBody Customer customer, @RequestBody String customerId) {
        return customerRepository.save(customer);
    }

    //Remove a certain customer by calling /customer/customerId
    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable String customerId) {
        customerRepository.deleteById(customerId);
    }

    //Remove all customers by sending a delete to /customer
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }


}
