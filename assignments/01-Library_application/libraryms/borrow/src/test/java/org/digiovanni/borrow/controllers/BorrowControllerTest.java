package org.digiovanni.borrow.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllBorrowShouldReturnOk() {
        try {
            mockMvc.perform(MockMvcRequestBuilders
                    .get("/v2/borrow")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}