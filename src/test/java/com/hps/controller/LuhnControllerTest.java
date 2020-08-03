package com.hps.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LuhnController.class)
class LuhnControllerTest {

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {}

    @AfterEach
    void tearDown() {}

    @Test
    void generateCheckDigit() throws Exception {
        String CONTEXT = "/luhn?cardNumber=92739871" ;
        mvc.perform(post(CONTEXT).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("9"));
    }

    @Test
    void isValidLuhn_OK() throws Exception {
        String CONTEXT = "/luhn/3566002020360505" ;
        mvc.perform(get(CONTEXT).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    void isValidLuhn_NOT_FOUND() throws Exception {
        String CONTEXT = "/luhn/3566002020360506" ;
        mvc.perform(get(CONTEXT).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isNotFound());
    }

    @Test
    void isValidLuhn_BAD_REQUEST() throws Exception {
        String CONTEXT = "/luhn/0" ;
        mvc.perform(get(CONTEXT).contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isBadRequest());
    }

    @Test
    void countRange_OK() throws Exception {
        String CONTEXT = "/luhn/927398710/927398720/count" ;
        mvc.perform(get(CONTEXT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("startNumber").value(927398719))
                .andExpect(jsonPath("endNumber").value(927398719))
                .andExpect(jsonPath("count").value(1));
    }

    @Test
    void retrieve() {

    }
}
