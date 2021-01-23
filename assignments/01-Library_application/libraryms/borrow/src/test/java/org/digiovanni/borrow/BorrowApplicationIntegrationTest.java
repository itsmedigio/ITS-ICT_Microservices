package org.digiovanni.borrow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.digiovanni.borrow.repos.BorrowRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BorrowApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BorrowRepository borrowRepository;

    @Test
    public void testGetAllBorrowsShouldReturnOk() throws Exception {
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