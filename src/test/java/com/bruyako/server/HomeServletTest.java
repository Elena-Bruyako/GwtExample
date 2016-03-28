package com.bruyako.server;

import com.bruyako.client.model.UserDto;
import com.bruyako.server.repository.UserRepository;
import com.bruyako.shared.User;
import com.bruyako.shared.UsernameNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brunyatko on 28.03.16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({HomeServlet.class, UserRepository.class})
public class HomeServletTest {

    private static final int USER_ID = 1;
    private static final String USER_NAME = "Ivan";
    private static final String USER_LOGIN = "ivan";
    private static final String USER_PASSWORD = "secret";
    private static final String USER_PASSWORD_HASH_SALT = "$2a$12$HrlbrFhM1m6fPiknbgpkNeTJ48D0NHZIvBlSk3Vtk3i.oubG4ogRS";

    private UserRepository userRepositoryMock = Mockito.mock(UserRepository.class);

    @Before
    public void setUp() throws Exception {
        User user = new User();
        user.setId(USER_ID);
        user.setName(USER_NAME);
        user.setLogin(USER_LOGIN);
        user.setPassword(USER_PASSWORD_HASH_SALT);

        List<User> users = new ArrayList<>();
        users.add(user);

        Mockito.when(userRepositoryMock.getAll()).thenReturn(users);
        PowerMockito.whenNew(UserRepository.class).withNoArguments().thenReturn(userRepositoryMock);
    }

    @Test
    public void knownUserLoginTest() {
        HomeServlet servlet = new HomeServlet();
        UserDto userDto = new UserDto(USER_LOGIN, USER_PASSWORD);
        String userName = servlet.getLoggedinUserName(userDto);
        Assert.assertEquals(USER_NAME, userName);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void unknownUserLoginTest() {
        HomeServlet servlet = new HomeServlet();
        UserDto userDto = new UserDto("john", "smith");
        servlet.getLoggedinUserName(userDto);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void invalidUsertPasswordTest() {
        HomeServlet servlet = new HomeServlet();
        UserDto userDto = new UserDto(USER_LOGIN, "smith");
        servlet.getLoggedinUserName(userDto);
    }

}
