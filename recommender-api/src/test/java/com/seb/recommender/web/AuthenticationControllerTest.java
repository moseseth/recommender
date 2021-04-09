package com.seb.recommender.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seb.recommender.security.models.AuthenticationRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationControllerTest {
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    ObjectMapper objectMapper;

    private MockMvc mvc;

    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mvc = builder.build();
    }


    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        AuthenticationRequest user = new AuthenticationRequest("musie", "2020");
        ResultMatcher ok = status().isOk();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/api/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        org.assertj.core.api.Assertions.assertThatThrownBy(() -> this.mvc.perform(builder)
                .andExpect(ok)).hasCause(new BadCredentialsException("Incorrect username or password"));;
    }

    @Test
    public void shouldAllowAccessToAuthenticatedUsers() throws Exception {
        AuthenticationRequest user = new AuthenticationRequest("sandbox", "seb2021");
        ResultMatcher ok = status().isOk();
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/api/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user));

        MvcResult result = this.mvc.perform(builder)
                .andExpect(ok).andReturn();

        String message = result.getResponse().getContentAsString();
        assertThat(message).contains("token");
    }

}
