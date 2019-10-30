package com.schibsted.spain.friends;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

public class FriendShipLegacy {
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @Before("string value")
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @Test
    public void first() throws Exception {
        mockMvc.perform(get("/friendship/list"));
                //.andExpect(status().isOk()).andExpect(view().name("employeeHome"))
        // .andExpect(model().attributeExists("employee")).andDo(print());
    }
}
