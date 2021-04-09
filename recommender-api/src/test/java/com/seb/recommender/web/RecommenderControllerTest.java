package com.seb.recommender.web;

import com.seb.recommender.security.services.UserService;
import com.seb.recommender.security.utils.JwtUtil;
import io.jsonwebtoken.Jwt;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class RecommenderControllerTest {
    private @Autowired
    MockMvc mvc;

    private @Autowired
    UserService userService;

    private @Autowired
    JwtUtil jwtUtil;

    @Test
    public void shouldGetForbiddenWithoutAuth() throws Exception {
        String url = "/api/products";
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isForbidden())
                .andReturn().getResponse();

        assertThat(response.getContentAsString()).isEmpty();
    }

    @Test
    public void shouldGetListOfProductsWithAuth() throws Exception {
        String url = "/api/products";
        final UserDetails userDetails = userService.loadUserByUsername("sandbox");
        final String jwtToken = "Bearer " + jwtUtil.generateToken(userDetails);

        Assert.assertNotNull(jwtToken);
        mvc.perform(MockMvcRequestBuilders.get(url)
                .header("Authorization", jwtToken))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"type\":\"Junior Saver Account\"}]"));
    }
}
