package com.bruyako.client;

import com.bruyako.client.exception.EmptyLoginException;
import com.bruyako.client.exception.EmptyLoginPasswordException;
import com.bruyako.client.exception.EmptyPasswordException;
import com.bruyako.client.model.UserDto;
import com.bruyako.client.utils.LoginValidator;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by brunyatko on 29.03.16.
 */
public class LoginValidatorTest {

    private static final String USER_LOGIN = "ivan";
    private static final String USER_PASSWORD = "secret";

    @Test
    public void validDataTest(){
        UserDto userDto = new UserDto(USER_LOGIN, USER_PASSWORD);
        Assert.assertTrue(LoginValidator.isValidUserData(userDto));
    }

    @Test(expected = EmptyLoginException.class)
    public void emptyLoginTest() {
        UserDto userDto = new UserDto("", USER_PASSWORD);
        LoginValidator.isValidUserData(userDto);
    }

    @Test(expected = EmptyPasswordException.class)
    public void emptyPasswordTest() {
        UserDto userDto = new UserDto(USER_LOGIN, "");
        LoginValidator.isValidUserData(userDto);
    }

    @Test(expected = EmptyLoginPasswordException.class)
    public void emptyLoginPaswordTest() {
        UserDto userDto = new UserDto("", "");
        LoginValidator.isValidUserData(userDto);
    }

    @Test(expected = EmptyLoginException.class)
    public void nullLoginTest() {
        UserDto userDto = new UserDto(null, USER_PASSWORD);
        LoginValidator.isValidUserData(userDto);
    }

    @Test(expected = EmptyPasswordException.class)
    public void nullPasswordTest() {
        UserDto userDto = new UserDto(USER_LOGIN, null);
        LoginValidator.isValidUserData(userDto);
    }

    @Test(expected = EmptyLoginPasswordException.class)
    public void nullLoginPaswordTest() {
        UserDto userDto = new UserDto(null, null);
        LoginValidator.isValidUserData(userDto);
    }

}
