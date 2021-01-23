package org.digiovanni.customer.repos;

import org.digiovanni.customer.models.Customer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CustomerRepositoryTest {

    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void testEmptyDB() {
        assertEquals(0, customerRepository.findAll().size());
    }

    @Test
    void addCustomerShouldReturnOneCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId("testjunit1");
        customer.setCustomerName("testname1");
        customer.setCustomerSurname("testsurname1");
        customer.setCustomerPhone("testphone1");
        customerRepository.save(customer);
        assertEquals(0, customerRepository.findAll().size());
    }

    @Test
    void deleteCustomerShouldReturnZeroCustomers() {
        Customer customer = new Customer();
        customer.setCustomerId("testjunit1");
        customer.setCustomerName("testname1");
        customer.setCustomerSurname("testsurname1");
        customer.setCustomerPhone("testphone1");
        customerRepository.save(customer);
        customerRepository.deleteById("testjunit1");
        assertEquals(0, customerRepository.findAll().size());
    }

}