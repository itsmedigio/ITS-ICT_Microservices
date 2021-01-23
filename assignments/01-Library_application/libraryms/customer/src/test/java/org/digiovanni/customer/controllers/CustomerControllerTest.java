package org.digiovanni.customer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllCustomerShouldReturnOk() {
        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/v2/customer")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}