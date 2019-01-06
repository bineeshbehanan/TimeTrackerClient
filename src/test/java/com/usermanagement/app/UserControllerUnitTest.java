package com.usermanagement.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.userdata.app.controllers.UserController;
import com.userdata.app.entities.User;
import com.userdata.app.service.UserService;

public class UserControllerUnitTest {

    private static UserController userController;
    private static UserService mockedUserService;
    private static BindingResult mockedBindingResult;
    private static Model mockedModel;

    @BeforeClass
    public static void setUpUserControllerInstance() {
        mockedBindingResult = mock(BindingResult.class);
        mockedModel = mock(Model.class);
        userController = new UserController(mockedUserService);
    }

    @Test
    public void whenCalledshowSignUpForm_thenCorrect() {
        User user = new User("john@domain.com",null, null);

        assertThat(userController.showSignUpForm(user)).isEqualTo("add-user");
    }
    
    @Test
    public void whenCalledaddUserAndInValidUser_thenCorrect() throws ParseException, IOException {
        User user = new User("john@domain.com",null, null);

        when(mockedBindingResult.hasErrors()).thenReturn(true);

        assertThat(userController.addUser(user, mockedBindingResult, mockedModel)).isEqualTo("add-user");
    }

}
