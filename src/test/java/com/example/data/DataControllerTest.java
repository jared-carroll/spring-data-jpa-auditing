package com.example.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DataController.class)
@ActiveProfiles("test")
public class DataControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DataRepository dataRepository;

    @Test
    public void postData() throws Exception {
        Data data = when(mock(Data.class).getId())
                .thenReturn(1L)
                .getMock();
        when(dataRepository.save(isA(Data.class)))
                .thenReturn(data);

        mockMvc.perform(post("/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"data\" : \"foo\"\n" +
                        "}")
                .with(httpBasic("admin", "admin")))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/data/1"));
    }
}