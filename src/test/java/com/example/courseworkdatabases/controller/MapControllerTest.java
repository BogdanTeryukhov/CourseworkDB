package com.example.courseworkdatabases.controller;

import com.example.courseworkdatabases.entity.Map;
import com.example.courseworkdatabases.entity.Set;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MapControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void addMap_ok_status() throws Exception {
        mockMvc.perform(
                        post("/map/add")
                                .content(asJsonString(new Map("Yukon", (short) 2, "Robin hood VS Bigfoot")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Map has been saved"));
    }

    @Test
    public void addMap_bad_request_status() throws Exception {
        mockMvc.perform(
                        post("/map/add")
                                .content(asJsonString(new Map("Sherwood forest", (short) 2, "Robin hood VS Bigfoot")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Map has been saved"));

        mockMvc.perform(
                        post("/map/add")
                                .content(asJsonString(new Map("Sherwood forest", (short) 2, "Robin hood VS Bigfoot")))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Map is already exists! You can`t make another map with same name"));
    }

    @Test
    public void deleteMap_ok_status() throws Exception {
        mockMvc.perform(
                        post("/map/add")
                                .content(asJsonString(new Map("Yukon", (short) 4, "Robin hood VS Bigfoot")))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Map has been saved"));

        mockMvc.perform(
                        delete("/map/delete/{name}", "Yukon")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("Map has been deleted"));
    }

    @Test
    public void deleteMap_bad_request_status() throws Exception {

        mockMvc.perform(
                        delete("/map/delete/{name}", "Yukon")
                )
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Map is not exists!"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}