package com.schibsted.spain.friends.unit;

import com.schibsted.spain.friends.model.User;
import com.schibsted.spain.friends.repository.UserRepository;
import com.schibsted.spain.friends.service.ValidationsService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginServiceTest {

    @TestConfiguration
    static class ValidationServiceTestContextConfiguration {
        @Bean
        public ValidationsService validationsService() {
            return new ValidationsService();
        }
    }

    @Autowired
    ValidationsService validationsService;
    @MockBean
    UserRepository userRepository;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void signUp() {
        User user = new User("MarioPeder", "pass123456");
        when(userRepository.save(any(User.class))).thenReturn(user);
    }


    @Test
    public void signInIncorrectPass() {
        // MOCK findByUsername
        List<User> users = new ArrayList<>();
        User user = new User("MarioMoran", "pass123456");
        users.add(user);
        Mockito.doReturn(users).when(userRepository).findByUsername("MarioMoran");

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("User or password incorrect");
        validationsService.validateUserWithPass("MarioMoran", "pass1234");
    }

    @Test
    public void signInIncorrectUser() {
        // MOCK findByUsername
        List<User> users = new ArrayList<>();
        User user = new User("MarioMoran", "pass123456");
        users.add(user);
        Mockito.doReturn(users).when(userRepository).findByUsername("MarioMoran");

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("User or password incorrect");
        validationsService.validateUserWithPass("Mario", "pass123456");
    }

}
