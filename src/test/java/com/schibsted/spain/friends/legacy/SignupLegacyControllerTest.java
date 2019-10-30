package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.repository.UserRepository;
import com.schibsted.spain.friends.service.LoginService;
import com.schibsted.spain.friends.service.ValidationsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;

@RunWith(SpringRunner.class)
@WebMvcTest (SignupLegacyController.class)
@EnableJpaRepositories("com.schibsted.spain.friends.repository")
public class SignupLegacyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;
    @MockBean
    LoginService loginService;
    @MockBean
    ValidationsService validationsService;
    @MockBean
    SignupLegacyController signupLegacyController;

    @Test
    public void contextLoads() throws Exception{
        Mockito.when(userRepository.findByUserName("")).thenReturn(
                Collections.emptyList()
        );
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/signup")
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        System.out.println(mvcResult.getResponse());
        Mockito.verify(userRepository).findAll();
    }
}
