package com.bruyako.client.utils;

import com.bruyako.client.exception.EmptyLoginException;
import com.bruyako.client.exception.EmptyLoginPasswordException;
import com.bruyako.client.exception.EmptyPasswordException;
import com.bruyako.client.model.UserDto;

/**
 * Created by brunyatko on 28.03.16.
 */
public class LoginValidator {

    public static boolean isValidUserData(UserDto user) throws RuntimeException {

        if ((user.getPassword() == null || user.getPassword().length() == 0) &&
                (user.getLogin() == null || user.getLogin().length() == 0))
            throw new EmptyLoginPasswordException();

        if (user.getLogin() == null || user.getLogin().length() == 0)
            throw new EmptyLoginException();

        if (user.getPassword() == null || user.getPassword().length() == 0)
            throw new EmptyPasswordException();

        return true;

    }

}
