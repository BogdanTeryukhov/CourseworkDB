package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Hero;
import com.example.courseworkdatabases.entity.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
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

import static org.junit.Assert.*;
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
public class HeroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addNewHero_ok_status() throws Exception {
        mockMvc.perform(
                        post("/hero/add")
                                .content(asJsonString(new Hero("Bigfoot", (short) 16, (short) 3, "When you end your turn in zone where are no opposing fighters, yo may draw a card",
                                        "Melee", "Jackalope", "Robin hood VS Bigfoot")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hero has been saved"));
    }

    @Test
    public void addNewHero_bad_request_status() throws Exception {
        mockMvc.perform(
                        post("/hero/add")
                                .content(asJsonString(new Hero("Bigfoot", (short) 16, (short) 3, "When you end your turn in zone where are no opposing fighters, yo may draw a card",
                                        "Melee", "Jackalope", "Robin hood VS Bigfoot")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hero has been saved"));

        mockMvc.perform(
                        post("/hero/add")
                                .content(asJsonString(new Hero("Bigfoot", (short) 2, (short) 2, "When you end your turn in zone where are no opposing fighters, yo may draw a card",
                                        "Melee", "Jackalope", "Robin hood VS Bigfoot")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Hero is already exists! You can`t make another hero with same name"));
    }

    @Test
    @Ignore
    public void deleteHero_ok_status() throws Exception {
        mockMvc.perform(
                        post("/hero/add")
                                .content(asJsonString(new Hero("Bigfoot", (short) 16, (short) 3, "When you end your turn in zone where are no opposing fighters, yo may draw a card",
                                        "Melee", "Jackalope", "Robin hood VS Bigfoot")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hero has been saved"));

        mockMvc.perform(
                        delete("/hero/delete/{name}", "Bigfoot")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hero has been deleted"));
    }

    @Test
    public void deleteHero_bad_request_status() throws Exception {
        mockMvc.perform(
                        delete("/hero/delete/{name}", "Bigfoot")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Hero is not exists!"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}