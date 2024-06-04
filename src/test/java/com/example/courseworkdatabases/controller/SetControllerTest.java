package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestPropertySource("classpath:application-test.properties")
@TestMethodOrder(OrderAnnotation.class)
public class SetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void addSet_ok_status() throws Exception {
        mockMvc.perform(
                        post("/set/add")
                                .content(asJsonString(new Set("For king and country", (short) 3, (short) 4)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Set has been saved"));
    }

    @Test
    @Order(2)
    public void addSet_bad_request_status() throws Exception {
        mockMvc.perform(
                        post("/set/add")
                                .content(asJsonString(new Set("Tales to amaze", (short) 4, (short) 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Set has been saved"));

        mockMvc.perform(
                        post("/set/add")
                                .content(asJsonString(new Set("Tales to amaze", (short) 2, (short) 1)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Set is already exists! You can`t make another set with same name"));
    }

    @Test
    @Order(3)
    public void updateSet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        post("/set/add")
                        .content(asJsonString(new Set("Tales to amaze", (short) 1, (short) 1)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Set has been saved"));

        mockMvc.perform(MockMvcRequestBuilders.
                        put("/set/update/{name}", "Tales to amaze")
                        .content(asJsonString(new Set("Tales to amaze", (short) 4, (short) 2)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("Set has been updated"));
    }

    @Test
    @Order(4)
    public void deleteSet_ok_status() throws Exception {
        mockMvc.perform(
                        post("/set/add")
                                .content(asJsonString(new Set("Battle of legends: Volume 1", (short) 4, (short) 2)))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Set has been saved"));

        mockMvc.perform(
                        delete("/set/delete/{name}", "Battle of legends: Volume 1")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Set has been deleted"));
    }

    @Test
    @Order(5)
    public void deleteSet_bad_request_status() throws Exception {
        mockMvc.perform(
                        delete("/set/delete/{name}", "Simple Dimple")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Set is not exists!"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}