package com.schibsted.spain.friends.legacy;

import com.schibsted.spain.friends.model.FriendshipRequest;
import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.FriendshipRequestRepository;
import com.schibsted.spain.friends.repository.UserRepository;
import com.schibsted.spain.friends.service.FriendshipService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendshipLegacyController.class)

//@ContextConfiguration({
  //      "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class FriendshipLegacyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserRepository userRepository;
    @MockBean
    FriendshipService friendshipService;
    @MockBean
    FriendshipRequestRepository friendshipRequestRepository;
    @MockBean
    FriendshipLegacyController friendshipLegacyController;
    @MockBean
    SignupLegacyController signupLegacyController;

    @Test
    public void listFriends() throws Exception{
        when(userRepository.findByUserName("")).thenReturn(Collections.emptyList());
        ArrayList<FriendshipRequest> friendshipRequests =  new ArrayList<>();
        Date date = new Date();
        FriendshipRequest friendshipRequest1 = new FriendshipRequest("Ruben", "Mario", "accepted", date, date);
        FriendshipRequest friendshipRequest2 = new FriendshipRequest("Mario", "Estefania", "accepted", date, date);
        FriendshipRequest friendshipRequest3 = new FriendshipRequest("Mario", "Sol", "declined", date, date);
        FriendshipRequest friendshipRequest4 = new FriendshipRequest("Gabriel", "Mario", "pending", date, date);
        friendshipRequests.add(friendshipRequest1);
        friendshipRequests.add(friendshipRequest2);
        friendshipRequests.add(friendshipRequest3);
        friendshipRequests.add(friendshipRequest4);

        //Mockito.doReturn(friendshipRequests).when(friendshipRequestRepository).findByUserFromOrUserToAndStatus("Mario", "accepted");
        when(friendshipRequestRepository.findByUserFromOrUserToAndStatus("Mario", "accepted")).thenReturn(friendshipRequests);

        System.out.println(">>>>>>>> inicio");

        List<User> users = new ArrayList<>();
        User user = new User("Mario", "pass");
        users.add(user);
        //Mockito.doReturn(users).when(userRepository).findByUserName("Mario");
        when(userRepository.findByUserName("Mario")).thenReturn(users);



        /*

        this.mockMvc.perform(get("/friendship/list")
                .param("username", "Mario")
                .header("X-Password", "pass")
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hola Mundo"));

*/

        ArrayList<String> responsesucces = new ArrayList<>();
        responsesucces.add("Ruben");
        responsesucces.add("Estefania");

        this.mockMvc.perform(get("/friendship/list")
                .param("username", "Mario")
                .header("X-Password", "pass")
                .accept(MediaType.APPLICATION_JSON)

        )

                .andExpect(status().isOk())
                .andDo(print());
                //.andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON_UTF8));
                //.andExpect(MockMvcResultMatchers.model().attribute("response", responsesucces) );


/*
        MvcResult mvcResult = mockMvc.perform(
                get("/friendship/list")
                        .param("username", "Mario")
                        .header("X-Password", "pass")
        ).andReturn();

        System.out.println(">>>>" + mvcResult);
        System.out.println(">>>>" + mvcResult.getResponse().toString());

        System.out.println(">>>>" + mvcResult.getResponse().equals(response));

        System.out.println(">>>>" + mvcResult.getResponse().getContentAsString() );
        Assert.assertEquals(friendshipRequests, friendshipRequestRepository.findByUserFromOrUserToAndStatus("Mario", "accepted"));
 */

    }

}
